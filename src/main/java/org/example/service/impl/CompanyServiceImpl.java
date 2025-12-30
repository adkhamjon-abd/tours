package org.example.service.impl;

import org.example.exception.CompanyAlreadyExistsException;
import org.example.exception.CompanyNotFoundException;
import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.example.service.abstractions.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    public Company getCompanyById(int id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));
        return company;
    }

    public Company createCompany(Company company) {
        companyRepository.findById(company.getId())
                .ifPresent(c -> {
                    throw new CompanyAlreadyExistsException("Company with such id already exists");
                });

        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompany(int id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));

        companyRepository.deleteById(id);
    }

    public Company updateCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));

        existingCompany.setName(updateCompany.getName());
        companyRepository.update(existingCompany);
        return existingCompany;

    }

    public Company patchCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));

        if (updateCompany.getName() != null){
            existingCompany.setName(updateCompany.getName());
        }

        companyRepository.update(existingCompany);
        return existingCompany;

    }
}
