package com._jasettlement.demo.service.serviceImpl;

import com._jasettlement.demo.entity.dto.WalletDto;
import com._jasettlement.demo.entity.mapper.WalletMapper;
import com._jasettlement.demo.entity.request.CreateWalletRequest;
import com._jasettlement.demo.model.Customer;
import com._jasettlement.demo.model.Wallet;
import com._jasettlement.demo.repository.CustomerRepository;
import com._jasettlement.demo.repository.WalletRepository;
import com._jasettlement.demo.service.WalletService;
import com._jasettlement.demo.util.WalletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class WalletServiceImplementation implements WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public WalletDto createWallet(CreateWalletRequest createWalletRequest) {

        Customer customer = customerRepository.findCustomerByEmail(createWalletRequest.getCustomerEmail()).orElseThrow(()->new RuntimeException("Customer not found in the database"));
        Wallet wallet = new Wallet();
        String currency = createWalletRequest.getCurrencyCode();

        wallet.setCurrencyCode(currency);
        wallet.setWalletName(WalletUtil.generateWalletName(currency));
        wallet.setWalletPublicId(WalletUtil.generateWalletPublicId());
        wallet.setWalletBalance(WalletUtil.defaultWalletBalance(currency));
        wallet.setLedgerBalance(WalletUtil.defaultLedgerBalance(currency));
        wallet.setServiceProvider(WalletUtil.defaultServiceProvider());
        wallet.setActive(true);
        wallet.setCreatedAt(WalletUtil.now());
        wallet.setUpdatedAt(WalletUtil.now());
        wallet.setCustomer(customer);

        walletRepository.save(wallet);

        return walletMapper.apply(wallet);
    }

    @Override
    public WalletDto getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(()->new RuntimeException("Wallet not found in the database"));

        return walletMapper.apply(wallet);
    }
}
