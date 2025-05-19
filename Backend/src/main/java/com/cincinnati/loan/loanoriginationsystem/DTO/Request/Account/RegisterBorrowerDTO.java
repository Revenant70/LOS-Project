package com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account;

import com.cincinnati.loan.loanoriginationsystem.Enums.Role;

public record RegisterBorrowerDTO(
        String borrowerName,
        String borrowerEmail,
        String borrowerPassword,
        Role role
)
{}
