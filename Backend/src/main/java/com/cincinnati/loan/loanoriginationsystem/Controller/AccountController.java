package com.cincinnati.loan.loanoriginationsystem.Controller;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account.LoginBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account.RegisterBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Response.BorrowerResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Enums.Role;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.UserAlreadyExistsException;
import com.cincinnati.loan.loanoriginationsystem.Mapper.BorrowerMapper;
import com.cincinnati.loan.loanoriginationsystem.Service.AccountService;
import com.cincinnati.loan.loanoriginationsystem.Service.JWTService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v0/account")
public class AccountController {

    private final AccountService accountService;
    private final BorrowerMapper borrowerMapper;
    private final JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerBorrower(@Valid @RequestBody RegisterBorrowerDTO registerBorrowerDTO) {
        BorrowerEntity borrower = borrowerMapper.registerDTOToEntity(registerBorrowerDTO);
        borrower.setRole(Role.USER);
        try {
            accountService.createAccount(borrower);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginBorrower(@Valid @RequestBody LoginBorrowerDTO loginBorrowerDTO) {
        BorrowerEntity borrower = new BorrowerEntity();
        borrower.setBorrowerEmail(loginBorrowerDTO.borrowerEmail());
        borrower.setBorrowerPassword(loginBorrowerDTO.borrowerPassword());

        try {
            BorrowerEntity authenticatedBorrower = accountService.loginAccount(borrower);
            String jwt = jwtService.generateToken(authenticatedBorrower);

            BorrowerResponseDTO borrowerResponseDTO = borrowerMapper.toResponseDTO(authenticatedBorrower);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                    .body(borrowerResponseDTO);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

}
