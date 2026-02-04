package com._jasettlement.demo.controllers;


import com._jasettlement.demo.entity.dto.TransactionDto;
import com._jasettlement.demo.entity.request.CreateTransactionRequest;
import com._jasettlement.demo.entity.request.CreateWalletTransferRequest;
import com._jasettlement.demo.entity.response.ApiResponse;
import com._jasettlement.demo.service.serviceImpl.TransactionServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceImplementation transactionService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest){
        ApiResponse apiResponse;
        try{
          TransactionDto transactionDto = transactionService.createTransaction(createTransactionRequest);
          apiResponse = ApiResponse.builder()
                  .timeStamp(ZonedDateTime.now())
                  .isSuccessful(true)
                  .data(transactionDto)
                  .status(HttpStatus.CREATED.value())
                  .message("Transaction Created Successfully").build();


          return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (Exception e) {
            apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(false)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Failed to create transaction: " + e.getMessage())
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> createWalletTransfer(@RequestBody CreateWalletTransferRequest createWalletTransferRequest){
        ApiResponse apiResponse;
        try{
                     ;
            apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(true)
                    .data(transactionService.createWalletTransfer(createWalletTransferRequest))
                    .status(HttpStatus.CREATED.value())
                    .message("Wallet Transfer Created Successfully").build();


            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (Exception e) {
            apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(false)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Failed to create wallet transfer: " + e.getMessage())
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }

}
