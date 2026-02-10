package org.example.service.abstractions;

import org.example.dto.CompanyDTO;
import org.example.dto.request.CreateCompanyRequest;
import org.example.dto.request.UpdateCompanyRequest;
import org.example.dto.request.UpdateTourRequest;
import org.example.dto.response.CompanyResponse;
import org.example.model.Company;

import java.util.List;

public interface CompanyService {
    public CompanyResponse getCompanyById(int id);
    public CompanyResponse createCompany(CreateCompanyRequest company);
    List<CompanyResponse> getAllCompanies();
    void deleteCompany(int id);
    CompanyResponse updateCompany(int id, UpdateCompanyRequest updateCompany);
    CompanyResponse patchCompany(int id, UpdateCompanyRequest updateCompany);

}
