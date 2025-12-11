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

    public Company getCompanyById(int id) {

        return companyRepository.findById(id);
    }

    public String createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public String deleteCompany(int id) {
        return companyRepository.deleteById(id);
    }

    public Company updateCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id);
        if (existingCompany == null){
            return null;
        }

        existingCompany.setName(updateCompany.getName());
        companyRepository.update(existingCompany);
        return existingCompany;

    }

    public Company patchCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id);

        if (existingCompany == null) {
            return null;
        }

        if (updateCompany.getName() != null){
            existingCompany.setName(updateCompany.getName());
        }

        companyRepository.update(existingCompany);
        return existingCompany;

    }
}
