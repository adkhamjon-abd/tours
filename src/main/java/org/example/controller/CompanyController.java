package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.request.CreateCompanyRequest;
import org.example.dto.request.UpdateCompanyRequest;
import org.example.dto.response.CompanyResponse;
import org.example.response.ApiResponse;
import org.example.service.abstractions.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyById(@PathVariable("id") int id) {
        CompanyResponse company = companyService.getCompanyById(id);
        return ResponseEntity.ok(new ApiResponse<>(company));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(@RequestBody @Valid CreateCompanyRequest company) {
        CompanyResponse created = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(created));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAll() {
        List<CompanyResponse> companies = companyService.getAllCompanies();

        return ResponseEntity.ok(new ApiResponse<>(companies));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(
            @PathVariable("id") int id,
            @RequestBody UpdateCompanyRequest updateCompany) {
        CompanyResponse existing = companyService.updateCompany(id, updateCompany);
        return ResponseEntity.ok(new ApiResponse<>(existing));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> patchCompany(
            @PathVariable("id") int id,
            @RequestBody UpdateCompanyRequest updateCompany
    ) {
        CompanyResponse company = companyService.patchCompany(id, updateCompany);
        return ResponseEntity.ok(new ApiResponse<>(company));
    }
}
