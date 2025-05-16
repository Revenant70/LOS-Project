package com.cincinnati.loan.loanoriginationsystem.DTO.Request;

import jakarta.validation.Valid;

public record CreateBorrowerDTO (
        String borrowerName,
        String borrowerEmail,
        String borrowerPhoneNumber,
        String borrowerDOB,
        String borrowerSSN,
        String borrowerPassword
)
{}
