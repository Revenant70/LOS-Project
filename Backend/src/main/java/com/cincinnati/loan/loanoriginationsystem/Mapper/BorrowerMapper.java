package com.cincinnati.loan.loanoriginationsystem.Mapper;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.LoginBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Request.RegisterBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Response.BorrowerResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    BorrowerEntity registerDTOToEntity(RegisterBorrowerDTO dto);
    BorrowerEntity loginDTOToEntity(LoginBorrowerDTO dto);

    BorrowerResponseDTO toResponseDTO(BorrowerEntity entity);
}
