package com._jasettlement.demo.entity.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record WalletDto(
        String walletName,
        String currencyCode,
        Long walletBalance,
        Long ledgerBalance,
        String walletPublicId,
        String serviceProvider,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long customerId,
        List<TransactionDto> transactionList
) {}

