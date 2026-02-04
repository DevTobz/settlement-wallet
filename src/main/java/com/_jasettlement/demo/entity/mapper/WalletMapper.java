package com._jasettlement.demo.entity.mapper;

import com._jasettlement.demo.entity.dto.TransactionDto;
import com._jasettlement.demo.entity.dto.WalletDto;
import com._jasettlement.demo.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class WalletMapper implements Function<Wallet, WalletDto> {
    @Override
    public WalletDto apply(Wallet wallet) {
        return WalletDto.builder()
                .walletName(wallet.getWalletName())
                .currencyCode(wallet.getCurrencyCode())
                .walletBalance(wallet.getWalletBalance())
                .ledgerBalance(wallet.getLedgerBalance())
                .walletPublicId(wallet.getWalletPublicId())
                .serviceProvider(wallet.getServiceProvider())
                .isActive(wallet.isActive())
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .customerId(wallet.getCustomer() != null ? wallet.getCustomer().getId() : null)
                .transactionList(
                        wallet.getTransactionList() != null
                                ? wallet.getTransactionList().stream()
                                .map(t -> TransactionDto.builder()
                                        .transactionRef(t.getTransactionRef())
                                        .type(t.getType())
                                        .amount(t.getAmount())
                                        .currencyCode(t.getCurrencyCode())
                                        .narration(t.getNarration())
                                        .status(t.getStatus())
                                        .idempotencyKey(t.getIdempotencyKey())
                                        .createdAt(t.getCreatedAt())
                                        .walletPublicId(wallet.getWalletPublicId())
                                        .build())
                                .collect(Collectors.toList())
                                : null
                )
                .build();
    }
}

