package org.example.dto.mapper;

import org.example.dto.CompanyDTO;
import org.example.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO companyToCompanyDTO(Company company);
}
