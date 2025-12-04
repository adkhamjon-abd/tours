package org.example.service;

import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    public String getCompanyById(int id) {
        Company company = companyRepository.findById(id);

        if (company == null){ return "Company not found"; }

        return "Company Name: " + company.getName() + "\n" +
                "Company ID: " + company.getId();
    }

    public String createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

}
