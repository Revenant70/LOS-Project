package com.cincinnati.loan.loanoriginationsystem.DTO.Response;

import com.cincinnati.loan.loanoriginationsystem.Enums.Role;

public record BorrowerResponseDTO(
        String borrowerEmail,
        String borrowerPhoneNumber,
        Role role
) {
}
