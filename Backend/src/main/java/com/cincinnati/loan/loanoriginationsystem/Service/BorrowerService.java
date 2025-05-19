package com.cincinnati.loan.loanoriginationsystem.Service;

import com.cincinnati.loan.loanoriginationsystem.DTO.Response.BorrowerResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.PasswordWasIncorrectException;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.UserAlreadyExistsException;
import com.cincinnati.loan.loanoriginationsystem.Mapper.BorrowerMapper;
import com.cincinnati.loan.loanoriginationsystem.Repository.BorrowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;
    private final PasswordEncoder passwordEncoder;

    public void createAccount(BorrowerEntity borrower) {
        if(borrower == null) {
            throw new IllegalArgumentException("Borrower cannot be null");
        }
        if(borrowerRepository.existsByBorrowerEmail(borrower.getBorrowerEmail()))
        {
            throw new UserAlreadyExistsException("User already exists");
        }

        borrower.setBorrowerPassword(passwordEncoder.encode(borrower.getBorrowerPassword()));

        borrowerRepository.save(borrower);
    }

    public BorrowerEntity loginAccount(BorrowerEntity borrower) {
        if(borrower == null) {
            throw new IllegalArgumentException("Borrower cannot be null");
        }
        else if (!borrowerRepository.existsByBorrowerEmail(borrower.getBorrowerEmail())) {
            throw new UsernameNotFoundException("User does not exist");
        }

        BorrowerEntity returnBorrowerEntity = borrowerRepository.findByBorrowerEmail(borrower.getBorrowerEmail());

        if (!passwordEncoder.matches(borrower.getBorrowerPassword(), returnBorrowerEntity.getBorrowerPassword())) {
            throw new PasswordWasIncorrectException("Password was incorrect");
        }

        return returnBorrowerEntity;
    }

}
