package org.example.service;

import org.example.exception.CompanyAlreadyExistsException;
import org.example.exception.CompanyNotFoundException;
import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    public Company getCompanyById(int id) {
        Company company = companyRepository.findById(id);
        if (company == null) throw new CompanyNotFoundException("Company with such id does not exist");
        return company;
    }

    public Company createCompany(Company company) {
        Company existing = companyRepository.findById(company.getId());
        if (existing != null){
            throw new CompanyAlreadyExistsException("Company with such id already exists");
        }
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompany(int id) {

        Company company = companyRepository.findById(id);

        if (company == null){
            throw new CompanyNotFoundException("Company with such id does not exist");
        }

        companyRepository.deleteById(id);
    }

    public Company updateCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id);

        if (existingCompany == null){
            throw new CompanyNotFoundException("Company with such id does not exist");
        }

        existingCompany.setName(updateCompany.getName());
        companyRepository.update(existingCompany);
        return existingCompany;

    }

    public Company patchCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id);

        if (existingCompany == null) {
            throw new CompanyNotFoundException("Company with such id does not exist");
        }

        if (updateCompany.getName() != null){
            existingCompany.setName(updateCompany.getName());
        }

        companyRepository.update(existingCompany);
        return existingCompany;

    }
}
