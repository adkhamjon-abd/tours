package org.example.service.impl;

import org.example.dto.mapper.CompanyMapper;
import org.example.dto.request.CreateCompanyRequest;
import org.example.dto.request.UpdateCompanyRequest;
import org.example.dto.response.CompanyResponse;
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

    public CompanyResponse getCompanyById(int id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        return companyMapper.toResponse(company);
    }

    public CompanyResponse createCompany(CreateCompanyRequest companyRequest) {
        Company company = companyMapper.toEntity(companyRequest);

        companyRepository.findById(company.getId())
                .ifPresent(c -> {
                    throw new CompanyAlreadyExistsException("Company with such id already exists");
                });

        return companyMapper.toResponse(companyRepository.save(company));
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    public void deleteCompany(int id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        companyRepository.deleteById(id);
    }

    public CompanyResponse updateCompany(int id, UpdateCompanyRequest updateCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        existingCompany.setName(updateCompany.getName());
        companyRepository.update(existingCompany);
        return companyMapper.toResponse(existingCompany);

    }

    public CompanyResponse patchCompany(int id, UpdateCompanyRequest updateCompany) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        if (updateCompany.getName() != null){
            existingCompany.setName(updateCompany.getName());
        }

        companyRepository.update(existingCompany);
        return companyMapper.toResponse(existingCompany);

    }
}
