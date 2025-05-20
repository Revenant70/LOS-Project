package com.cincinnati.loan.loanoriginationsystem.Exceptions;

public class DuplicateEmploymentException extends RuntimeException {
    public DuplicateEmploymentException(String employerName) {
        super("Employment with employer '" + employerName + "' already exists for this borrower.");
    }
}
