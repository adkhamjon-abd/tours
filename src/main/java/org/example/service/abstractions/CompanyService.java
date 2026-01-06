package org.example.service.abstractions;

import org.example.dto.CompanyDTO;
import org.example.model.Company;

import java.util.List;

public interface CompanyService {
    public CompanyDTO getCompanyById(int id);
    public CompanyDTO createCompany(Company company);
    List<CompanyDTO> getAllCompanies();
    void deleteCompany(int id);
    CompanyDTO updateCompany(int id, Company updateCompany);
    CompanyDTO patchCompany(int id, Company updateCompany);

}
