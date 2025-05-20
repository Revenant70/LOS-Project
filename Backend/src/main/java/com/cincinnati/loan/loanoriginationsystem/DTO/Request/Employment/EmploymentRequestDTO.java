package com.cincinnati.loan.loanoriginationsystem.DTO.Request.Employment;


import com.cincinnati.loan.loanoriginationsystem.Enums.EmploymentDuration;

public record EmploymentRequestDTO(
        String employerName,
        String employerAddress,
        String jobTitle,
        EmploymentDuration employmentDuration
){
}
