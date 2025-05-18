package com.cincinnati.loan.loanoriginationsystem.Controller;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.RegisterBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.UserAlreadyExistsException;
import com.cincinnati.loan.loanoriginationsystem.Mapper.BorrowerMapper;
import com.cincinnati.loan.loanoriginationsystem.Service.BorrowerService;
import com.cincinnati.loan.loanoriginationsystem.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v0/account")
public class BorrowerController {

    private final BorrowerService borrowerService;
    private final BorrowerMapper borrowerMapper;

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<?> registerBorrower(@Valid @RequestBody RegisterBorrowerDTO createBorrowerDTO) {
        BorrowerEntity borrower = borrowerMapper.registerDTOToEntity(createBorrowerDTO);
        borrower.setRole(Role.USER);
        try {
            borrowerService.createAccount(borrower);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

    /*@RequestMapping("/login")
    @PostMapping
    public ResponseEntity<?> loginBorrower(@Valid @RequestBody CreateBorrowerDTO createBorrowerDTO) {
        BorrowerEntity borrower = new BorrowerEntity();
        borrower.setBorrowerEmail(createBorrowerDTO.borrowerEmail());
        borrower.setBorrowerName(createBorrowerDTO.borrowerName());
        borrower.setBorrowerPhoneNumber(createBorrowerDTO.borrowerPhoneNumber());

        try {
            borrowerService.(borrower);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }*/
}