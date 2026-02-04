package com._jasettlement.demo.controllers;


import com._jasettlement.demo.entity.dto.WalletDto;
import com._jasettlement.demo.entity.request.CreateWalletRequest;
import com._jasettlement.demo.entity.response.ApiResponse;
import com._jasettlement.demo.service.serviceImpl.WalletServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletServiceImplementation walletServiceImplementation;


    @PostMapping("/")
    public ResponseEntity<ApiResponse> createWallet(@RequestBody CreateWalletRequest createWalletRequest){
        ApiResponse apiResponse;
        try{
            WalletDto wallet = walletServiceImplementation.createWallet(createWalletRequest);
           apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(true)
                    .status(HttpStatus.CREATED.value())
                    .message("Wallet created successfully")
                    .data(wallet)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

        } catch (Exception e) {
             apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(false)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Failed to create wallet: " + e.getMessage())
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getWalletById(@PathVariable Long id) {
        ApiResponse apiResponse;

        try {

            WalletDto wallet = walletServiceImplementation.getWalletById(id);

            if (wallet == null) {
                apiResponse = ApiResponse.builder()
                        .timeStamp(ZonedDateTime.now())
                        .isSuccessful(false)
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Wallet with ID " + id + " not found")
                        .data(null)
                        .build();

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }


            apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(true)
                    .status(HttpStatus.OK.value())
                    .message("Wallet retrieved successfully")
                    .data(wallet)
                    .build();

            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .isSuccessful(false)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Failed to retrieve wallet: " + e.getMessage())
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }





}
