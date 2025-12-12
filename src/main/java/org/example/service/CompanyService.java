package org.example.service;

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

    public ResponseEntity<?> getCompanyById(int id) {
        Company company = companyRepository.findById(id);
        if (company == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company with such id does not exist");
        }
        return ResponseEntity.ok(company);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public ResponseEntity<?> deleteCompany(int id) {

        Company company = companyRepository.findById(id);

        if (company == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company with such id does not exist");
        }

        companyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> updateCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id);

        if (existingCompany == null){
            return ResponseEntity.notFound().build();
        }

        existingCompany.setName(updateCompany.getName());
        companyRepository.update(existingCompany);
        return ResponseEntity.ok(existingCompany);

    }

    public ResponseEntity<?> patchCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id);

        if (existingCompany == null) {
            return ResponseEntity.notFound().build();
        }

        if (updateCompany.getName() != null){
            existingCompany.setName(updateCompany.getName());
        }

        companyRepository.update(existingCompany);
        return ResponseEntity.ok(existingCompany);

    }
}
