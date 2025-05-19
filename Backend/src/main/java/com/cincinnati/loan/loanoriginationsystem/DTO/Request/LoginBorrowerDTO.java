package com.cincinnati.loan.loanoriginationsystem.DTO.Request;

import com.cincinnati.loan.loanoriginationsystem.Enums.Role;

public record LoginBorrowerDTO (
        String borrowerEmail,
        String borrowerPassword
){}
