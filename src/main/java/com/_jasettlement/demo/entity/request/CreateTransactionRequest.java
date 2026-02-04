package com._jasettlement.demo.entity.request;


import com._jasettlement.demo.Enum.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {
    private String walletPublicId;
    private String key;
    private Long amount;
    private TransactionType transactionType;
}
