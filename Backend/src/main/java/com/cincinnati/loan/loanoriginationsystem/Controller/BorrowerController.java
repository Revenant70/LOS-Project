package com.cincinnati.loan.loanoriginationsystem.Controller;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.BorrowerInfo.InfoBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import com.cincinnati.loan.loanoriginationsystem.Exceptions.UserAlreadyExistsException;
import com.cincinnati.loan.loanoriginationsystem.Mapper.BorrowerMapper;
import com.cincinnati.loan.loanoriginationsystem.Service.BorrowerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@AllArgsConstructor
@RequestMapping("/api/v0/loan/borrower")
@RestController
public class BorrowerController {

    private final BorrowerService borrowerService;
    private final BorrowerMapper borrowerMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createBorrowerBaseline(@Valid @RequestBody InfoBorrowerDTO infoBorrowerDTO) {
        BorrowerEntity borrower = borrowerMapper.infoBorrowerDTOToEntity(infoBorrowerDTO);

        try {
            borrowerService.createBorrowerBaseline(borrower);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

}
