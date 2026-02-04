package com._jasettlement.demo.entity.mapper;

import com._jasettlement.demo.entity.dto.TransactionDto;
import com._jasettlement.demo.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.function.Function;



@Service
public class TransactionMapper implements Function<Transaction, TransactionDto> {

    @Override
    public TransactionDto apply(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getTransactionRef(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getCurrencyCode(),
                transaction.getNarration(),
                transaction.getStatus(),
                transaction.getIdempotencyKey(),
                transaction.getCreatedAt(),
                transaction.getWallet() != null ? transaction.getWallet().getWalletPublicId() : null


        );
    }
}
