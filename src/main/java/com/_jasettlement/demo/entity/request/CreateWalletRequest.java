package com._jasettlement.demo.entity.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWalletRequest {
    private String walletName;
    private String currencyCode;
    private Double walletBalance;
    private Double ledgerBalance;
    private String walletPublicId;
    private String serviceProvider;
}
