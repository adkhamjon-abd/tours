package org.example.service.abstractions;

import org.example.model.Company;

import java.util.List;

public interface CompanyService {
    public Company getCompanyById(int id);
    public Company createCompany(Company company);
    List<Company> getAllCompanies();
    void deleteCompany(int id);
    Company updateCompany(int id, Company updateCompany);
    Company patchCompany(int id, Company updateCompany);

}
