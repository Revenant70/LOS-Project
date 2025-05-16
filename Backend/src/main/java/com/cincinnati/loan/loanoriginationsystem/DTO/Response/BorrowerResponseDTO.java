package com.cincinnati.loan.loanoriginationsystem.DTO.Response;

public record BorrowerResponseDTO(
        String borrowerUsername,
        String borrowerPhoneNumber,
        String borrowerEmail
) {
}
