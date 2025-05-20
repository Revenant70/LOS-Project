package com.cincinnati.loan.loanoriginationsystem.Mapper;

import com.cincinnati.loan.loanoriginationsystem.DTO.Request.Employment.EmploymentRequestDTO;
import com.cincinnati.loan.loanoriginationsystem.Entity.EmploymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmploymentMapper {

    EmploymentEntity EmploymentRequestDTOToEntity(EmploymentRequestDTO employmentRequestDTO);

}
