package com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account;

public record LoginBorrowerRequestDTO(
        String borrowerEmail,
        String borrowerPassword
){}
