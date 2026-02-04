package com._jasettlement.demo.entity.dto;

import com._jasettlement.demo.Enum.TransactionStatus;
import com._jasettlement.demo.Enum.TransactionType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TransactionDto(
        Long id,
        String transactionRef,
        TransactionType type,
        Long amount,
        String currencyCode,
        String narration,
        TransactionStatus status,
        String idempotencyKey,
        LocalDateTime createdAt,
        String walletPublicId
) {}
