package com._jasettlement.demo.service;

import com._jasettlement.demo.entity.dto.TransactionDto;
import com._jasettlement.demo.entity.request.CreateTransactionRequest;
import com._jasettlement.demo.entity.request.CreateWalletTransferRequest;

public interface TransactionService {
    TransactionDto createTransaction(CreateTransactionRequest createTransactionRequest);
    Object createWalletTransfer(CreateWalletTransferRequest createWalletTransferRequest);
}
