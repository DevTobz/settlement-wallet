package com._jasettlement.demo.entity.mapper;

import com._jasettlement.demo.entity.dto.CustomerDto;
import com._jasettlement.demo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerMapper implements Function<Customer, CustomerDto> {

    @Override
    public CustomerDto apply(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }
}
