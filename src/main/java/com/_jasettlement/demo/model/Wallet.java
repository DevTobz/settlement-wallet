package com._jasettlement.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    @SequenceGenerator(
            name = "wallet_sequence",
            sequenceName = "wallet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wallet_sequence"
    )
    @Column(name = "wallet_Id", updatable = false, nullable = false)
    private Long id;
    private String walletName;
    private String currencyCode;
    private Long walletBalance;
    private Long ledgerBalance;
    private String walletPublicId;
    private String serviceProvider;
    private boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
