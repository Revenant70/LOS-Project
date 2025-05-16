package com.cincinnati.loan.loanoriginationsystem.Service;

import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.UserAlreadyExistsException;
import com.cincinnati.loan.loanoriginationsystem.Repository.BorrowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public void createAccount(BorrowerEntity borrower) {
        if(borrower == null) {
            throw new IllegalArgumentException("Borrower cannot be null");
        }
        if(borrowerRepository.existsByBorrowerEmail(borrower.getBorrowerEmail()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        borrowerRepository.save(borrower);
    }

}
