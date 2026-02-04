package com._jasettlement.demo.config;

import com._jasettlement.demo.model.Customer;
import com._jasettlement.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final CustomerRepository customerRepository;

    @Bean
    CommandLineRunner seedCustomer() {
        return args -> {
            if (customerRepository.count() > 0) {
                return;
            }

            Customer customer = Customer.builder()
                    .firstName("Akins")
                    .lastName("Toby")
                    .email("AkinsToby@example.com")
                    .phoneNumber("+2348012349078")
                    .build();

            customerRepository.save(customer);
        };
    }
}
