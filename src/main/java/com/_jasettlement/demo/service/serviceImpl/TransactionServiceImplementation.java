package com._jasettlement.demo.service.serviceImpl;
import com._jasettlement.demo.Enum.TransactionStatus;
import com._jasettlement.demo.Enum.TransactionType;
import com._jasettlement.demo.entity.dto.TransactionDto;
import com._jasettlement.demo.entity.exceptions.DuplicateTransactionException;
import com._jasettlement.demo.entity.mapper.TransactionMapper;
import com._jasettlement.demo.entity.request.CreateTransactionRequest;
import com._jasettlement.demo.entity.request.CreateWalletTransferRequest;
import com._jasettlement.demo.model.Transaction;
import com._jasettlement.demo.model.Wallet;
import com._jasettlement.demo.repository.CustomerRepository;
import com._jasettlement.demo.repository.TransactionRepository;
import com._jasettlement.demo.repository.WalletRepository;
import com._jasettlement.demo.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;



    @Override
    @Transactional
    public TransactionDto createTransaction(CreateTransactionRequest request) {


        Wallet wallet = walletRepository
                .findByWalletPublicIdForUpdate(request.getWalletPublicId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));


        Optional<Transaction> existingTransaction = transactionRepository
                .findByWalletIdAndIdempotencyKey(wallet.getId(), request.getKey());

        if (existingTransaction.isPresent()) {

            throw new DuplicateTransactionException(
                    "Duplicate transaction detected for idempotency key: " + request.getKey()
            );
        }


        long amount = request.getAmount();
        if (request.getTransactionType() == TransactionType.DEBIT) {
            // For debit
            amount = -Math.abs(amount);
        } else {
            // For credit
            amount = Math.abs(amount);
        }


        if (amount < 0 && wallet.getWalletBalance() + amount < 0) {
            throw new RuntimeException("Insufficient balance");
        }


        wallet.setWalletBalance(wallet.getWalletBalance() + amount);
        wallet.setLedgerBalance(wallet.getLedgerBalance() + amount);
        wallet = walletRepository.saveAndFlush(wallet);



        Transaction tx = Transaction.builder()
                .wallet(wallet)
                .transactionRef(UUID.randomUUID().toString())
                .type(request.getTransactionType())
                .amount(amount)
                .currencyCode(wallet.getCurrencyCode())
                .narration(request.getKey())
                .status(TransactionStatus.SUCCESS)
                .customer(wallet.getCustomer())
                .idempotencyKey(request.getKey())
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(tx);


        return transactionMapper.apply(tx);
    }


    @Override
    @Transactional
    public Object createWalletTransfer(CreateWalletTransferRequest createWalletTransferRequest) {
        Wallet senderWallet =  walletRepository.findByWalletPublicIdForUpdate(createWalletTransferRequest.getSendingWalletPublicId()).orElseThrow(()->new RuntimeException("Sender wallet not found"));
        Wallet receiverWallet = walletRepository.findByWalletPublicIdForUpdate((createWalletTransferRequest.getReceivingWalletPublicId())).orElseThrow(()->new RuntimeException("Reciever wallet not found"));

        String baseKey = createWalletTransferRequest.getKey();

        String debitKey = baseKey + ":DEBIT";

        Optional<Transaction> existingTransaction =
                transactionRepository.findByWalletIdAndIdempotencyKey(
                        senderWallet.getId(),
                        debitKey
                );

        if(existingTransaction.isPresent()){
            throw new DuplicateTransactionException(
                    "Duplicate transaction detected for idempotency key: " + createWalletTransferRequest.getKey()
            );
        }

        if(senderWallet.getWalletBalance() < createWalletTransferRequest.getAmount()){
            throw new RuntimeException("Insufficient balance in sender wallet");
        }


        senderWallet.setWalletBalance(senderWallet.getWalletBalance() - createWalletTransferRequest.getAmount());
        senderWallet.setLedgerBalance(senderWallet.getLedgerBalance() - createWalletTransferRequest.getAmount());
        walletRepository.save(senderWallet);

        receiverWallet.setWalletBalance(receiverWallet.getWalletBalance() + createWalletTransferRequest.getAmount());
        receiverWallet.setLedgerBalance(receiverWallet.getLedgerBalance() + createWalletTransferRequest.getAmount());
        walletRepository.save(receiverWallet);


        Transaction tx = Transaction.builder()
                .wallet(senderWallet)
                .transactionRef(UUID.randomUUID().toString())
                .type(TransactionType.DEBIT)
                .amount(createWalletTransferRequest.getAmount())
                .currencyCode(senderWallet.getCurrencyCode())
                .narration("Transfer to wallet " + receiverWallet.getWalletPublicId())
                .status(TransactionStatus.SUCCESS)
                .customer(senderWallet.getCustomer())
                .idempotencyKey(baseKey + ":DEBIT")
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(tx);


        Transaction creditTx = Transaction.builder()
                .wallet(receiverWallet)
                .transactionRef(UUID.randomUUID().toString())
                .type(TransactionType.CREDIT)
                .amount(createWalletTransferRequest.getAmount())
                .currencyCode(receiverWallet.getCurrencyCode())
                .narration("Received from wallet " + senderWallet.getWalletPublicId())
                .status(TransactionStatus.SUCCESS)
                .customer(receiverWallet.getCustomer())
                .idempotencyKey(baseKey + ":CREDIT")
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(creditTx);


        return transactionMapper.apply(tx);
    }
}