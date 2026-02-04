//package com._jasettlement.demo.config;
//
//import com._jasettlement.demo.model.Customer;
//import com._jasettlement.demo.model.Wallet;
//import com._jasettlement.demo.repository.CustomerRepository;
//import com._jasettlement.demo.repository.WalletRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Configuration
//@RequiredArgsConstructor
//public class DataSeeder {
//
//    private final CustomerRepository customerRepository;
//    private final WalletRepository walletRepository;
//
//    @Bean
//    CommandLineRunner seedCustomerAndWallet() {
//        return args -> {
//
//            Customer customer = customerRepository
//                    .findCustomerByEmail("AkinsToby@example.com")
//                    .orElseGet(() -> customerRepository.save(
//                            Customer.builder()
//                                    .firstName("Akins")
//                                    .lastName("Toby")
//                                    .email("AkinsToby@example.com")
//                                    .phoneNumber("+2348012349078")
//                                    .build()
//                    ));
//
//            walletRepository
//                    .findByCustomerIdAndCurrencyCode(customer.getId(), "NGN")
//                    .orElseGet(() -> walletRepository.save(
//                            Wallet.builder()
//                                    .walletName("Akins NGN Wallet")
//                                    .currencyCode("NGN")
//                                    .walletBalance(100_000L)
//                                    .ledgerBalance(100_000L)
//                                    .walletPublicId(UUID.randomUUID().toString())
//                                    .serviceProvider("INTERNAL")
//                                    .isActive(true)
//                                    .customer(customer)
//                                    .createdAt(LocalDateTime.now())
//                                    .updatedAt(LocalDateTime.now())
//                                    .build()
//                    ));
//        };
//    }
//}
