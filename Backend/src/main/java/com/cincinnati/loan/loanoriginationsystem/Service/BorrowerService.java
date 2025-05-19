package com.cincinnati.loan.loanoriginationsystem.Service;

import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Enums.Role;
import com.cincinnati.loan.loanoriginationsystem.Repository.BorrowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final PasswordEncoder passwordEncoder;

    public void createBorrowerBaseline(BorrowerEntity borrower) {
        if(borrower == null) {
            throw new IllegalArgumentException("Borrower cannot be null");
        }
        if(borrowerRepository.existsByBorrowerEmail(borrower.getBorrowerEmail()))
        {
            BorrowerEntity dbBorrower = borrowerRepository.findByBorrowerEmail(borrower.getBorrowerEmail());
            dbBorrower.setBorrowerName(borrower.getBorrowerName());
            dbBorrower.setBorrowerDOB(borrower.getBorrowerDOB());
            dbBorrower.setBorrowerSSN(borrower.getBorrowerSSN());
            dbBorrower.setBorrowerPhoneNumber(borrower.getBorrowerPhoneNumber());
            dbBorrower.setRole(Role.USER);
        }
        else {
            borrower.setBorrowerPassword(passwordEncoder.encode(borrower.getBorrowerPassword()));
            borrower.setBorrowerEmail(borrower.getBorrowerEmail());
            borrower.setBorrowerName(borrower.getBorrowerName());
            borrower.setBorrowerDOB(borrower.getBorrowerDOB());
            borrower.setBorrowerSSN(borrower.getBorrowerSSN());
            borrower.setBorrowerPhoneNumber(borrower.getBorrowerPhoneNumber());
            borrower.setRole(Role.USER);
            if(borrower.getBorrowerDOB() == null) {
                throw new IllegalArgumentException("Borrower's date of birth cannot be null");
            }
            if(borrower.getBorrowerSSN() == null) {
                throw new IllegalArgumentException("Borrower's SSN cannot be null");
            }
        }

        borrowerRepository.save(borrower);

    }

}
