package org.example.service.impl;

import org.example.dto.CompanyDTO;
import org.example.dto.mapper.CompanyMapper;
import org.example.exception.CompanyAlreadyExistsException;
import org.example.exception.CompanyNotFoundException;
import org.example.model.Company;
import org.example.repository.CompanyRepository;
import org.example.service.abstractions.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;

        this.companyMapper = companyMapper;
    }

    public CompanyDTO getCompanyById(int id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));
        return companyMapper.companyToCompanyDTO(company);
    }

    public CompanyDTO createCompany(Company company) {
        companyRepository.findById(company.getId())
                .ifPresent(c -> {
                    throw new CompanyAlreadyExistsException("Company with such id already exists");
                });

        return companyMapper.companyToCompanyDTO(companyRepository.save(company));
    }

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyMapper::companyToCompanyDTO).collect(Collectors.toList());
    }

    public void deleteCompany(int id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));

        companyRepository.deleteById(id);
    }

    public CompanyDTO updateCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));

        existingCompany.setName(updateCompany.getName());
        companyRepository.update(existingCompany);
        return companyMapper.companyToCompanyDTO(existingCompany);

    }

    public CompanyDTO patchCompany(int id, Company updateCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with such id does not exist"));

        if (updateCompany.getName() != null){
            existingCompany.setName(updateCompany.getName());
        }

        companyRepository.update(existingCompany);
        return companyMapper.companyToCompanyDTO(existingCompany);

    }
}
