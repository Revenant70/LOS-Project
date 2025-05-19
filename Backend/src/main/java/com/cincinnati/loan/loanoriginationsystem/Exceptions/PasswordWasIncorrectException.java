package com.cincinnati.loan.loanoriginationsystem.Exceptions;

public class PasswordWasIncorrectException extends RuntimeException {
    public PasswordWasIncorrectException(String message) {
        super(message);
    }
}
