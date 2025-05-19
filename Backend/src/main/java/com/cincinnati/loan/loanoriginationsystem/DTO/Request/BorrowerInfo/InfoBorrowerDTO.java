package com.cincinnati.loan.loanoriginationsystem.DTO.Request.BorrowerInfo;

import com.cincinnati.loan.loanoriginationsystem.Enums.Role;

import java.time.LocalDate;

public record InfoBorrowerDTO(
    String borrowerName,
    LocalDate borrowerDOB,
    String borrowerSSN,
    String borrowerPhoneNumber,
    String borrowerEmail,
    String borrowerPassword,
    Role role
){
}
