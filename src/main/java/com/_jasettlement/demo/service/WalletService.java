package com._jasettlement.demo.service;

import com._jasettlement.demo.entity.dto.WalletDto;
import com._jasettlement.demo.entity.request.CreateWalletRequest;

public interface WalletService {
    WalletDto createWallet (CreateWalletRequest createWalletRequest);
    WalletDto getWalletById (Long id);
}
