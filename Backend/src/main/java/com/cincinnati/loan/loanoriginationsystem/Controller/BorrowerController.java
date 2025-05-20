package com.cincinnati.loan.loanoriginationsystem.Controller;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Borrower.BorrowerRequestDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Response.Borrower.BorrowerResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Response.LoanApplication.LoanApplicationResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Entity.LoanApplicationEntity;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.UserAlreadyExistsException;
import com.cincinnati.loan.loanoriginationsystem.Mapper.BorrowerMapper;
import com.cincinnati.loan.loanoriginationsystem.Repository.LoanApplicationRepository;
import com.cincinnati.loan.loanoriginationsystem.Service.BorrowerService;
import com.cincinnati.loan.loanoriginationsystem.Service.JWTService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Data
@AllArgsConstructor
@RequestMapping("/api/v0/loan/borrower")
@RestController
public class BorrowerController {

    private final BorrowerService borrowerService;
    private final BorrowerMapper borrowerMapper;
    private final JWTService jwtService;
    private final LoanApplicationRepository loanApplicationRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createBorrowerBaseline(@Valid @RequestBody BorrowerRequestDTO borrowerRequestDTO) {
        BorrowerEntity borrower = borrowerMapper.infoBorrowerDTOToEntity(borrowerRequestDTO);

        try {
            BorrowerEntity authenticatedUser = borrowerService.createBorrowerBaseline(borrower);
            String jwt = jwtService.generateToken(authenticatedUser);
            BorrowerResponseDTO borrowerResponseDTO = borrowerMapper.toResponseDTO(authenticatedUser);

            // Creating a new loan application for the borrower
            LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity();
            loanApplicationEntity.setBorrowerEntity(authenticatedUser);
            LoanApplicationEntity dbLoanApplicationEntity = loanApplicationRepository.save(loanApplicationEntity);
            authenticatedUser.getLoanApplicationEntities().add(dbLoanApplicationEntity);

            LoanApplicationResponseDTO loanApplicationResponseDTO = new LoanApplicationResponseDTO(loanApplicationEntity.getLoan_application_id(), borrowerResponseDTO);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                    .body(loanApplicationResponseDTO);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
