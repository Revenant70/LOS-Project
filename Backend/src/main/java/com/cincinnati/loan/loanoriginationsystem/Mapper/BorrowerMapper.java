package com.cincinnati.loan.loanoriginationsystem.Mapper;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account.LoginBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Account.RegisterBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Request.BorrowerInfo.InfoBorrowerDTO;
import com.cincinnati.loan.loanoriginationsystem.DTO.Response.BorrowerResponseDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    BorrowerEntity registerDTOToEntity(RegisterBorrowerDTO dto);
    BorrowerEntity loginDTOToEntity(LoginBorrowerDTO dto);

    BorrowerEntity infoBorrowerDTOToEntity(InfoBorrowerDTO dto);

    BorrowerResponseDTO toResponseDTO(BorrowerEntity entity);
}
