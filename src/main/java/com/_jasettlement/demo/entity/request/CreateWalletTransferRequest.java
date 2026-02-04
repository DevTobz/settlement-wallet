package com._jasettlement.demo.entity.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWalletTransferRequest {
    private String sendingWalletPublicId;
    private String receivingWalletPublicId;
    private String key;
    private Long amount;
}
