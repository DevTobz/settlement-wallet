package com._jasettlement.demo.service.serviceImpl;

import com._jasettlement.demo.entity.request.CreateWalletRequest;
import com._jasettlement.demo.repository.WalletRepository;
import com._jasettlement.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WalletServiceImplementation implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Object createWallet(CreateWalletRequest createWalletRequest) {
        return null;
    }
}
