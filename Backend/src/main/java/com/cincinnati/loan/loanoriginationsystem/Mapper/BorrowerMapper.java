package com.cincinnati.loan.loanoriginationsystem.Mapper;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account.LoginBorrowerRequestDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account.RegisterBorrowerRequestDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Borrower.BorrowerRequestDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Response.Borrower.BorrowerResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    BorrowerEntity registerDTOToEntity(RegisterBorrowerRequestDTO dto);
    BorrowerEntity loginDTOToEntity(LoginBorrowerRequestDTO dto);

    BorrowerEntity infoBorrowerDTOToEntity(BorrowerRequestDTO dto);

    BorrowerResponseDTO toResponseDTO(BorrowerEntity entity);
}
