package com._jasettlement.demo.entity.dto;

import com._jasettlement.demo.entity.dto.WalletDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CustomerDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {}
