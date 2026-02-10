package org.example.dto.mapper;

import org.example.dto.CompanyDTO;
import org.example.dto.request.CreateCompanyRequest;
import org.example.dto.request.UpdateCompanyRequest;
import org.example.dto.response.CompanyResponse;
import org.example.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO companyToCompanyDTO(Company company);

    Company toEntity(CreateCompanyRequest createCompanyRequest);

    Company toEntity(UpdateCompanyRequest updateCompanyRequest);

    CompanyResponse toResponse(Company company);
}
