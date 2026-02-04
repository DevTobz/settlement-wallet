package com._jasettlement.demo.model;

import com._jasettlement.demo.Enum.TransactionStatus;
import com._jasettlement.demo.Enum.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    @Column(updatable = false, nullable = false)
    private Long id;

    // Public reference for APIs
    @Column(unique = true, nullable = false)
    private String transactionRef;

    // CREDIT or DEBIT
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    // Amount in minor units (kobo / cents)
    private Long amount;

    private String currencyCode;

    private String narration;

    // SUCCESS, PENDING, FAILED
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    private LocalDateTime createdAt;
}
