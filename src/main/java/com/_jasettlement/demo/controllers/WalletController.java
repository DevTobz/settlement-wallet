package com._jasettlement.demo.controllers;


import com._jasettlement.demo.entity.request.CreateWalletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletServiceImpl walletservice;


    public ResponseEntity<?> createWallet(@RequestBody CreateWalletRequest createWalletRequest){

    }

}
