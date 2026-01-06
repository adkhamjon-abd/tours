package org.example.controller;

import org.example.dto.CompanyDTO;
import org.example.model.Company;
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
    public ResponseEntity<ApiResponse<CompanyDTO>> getCompanyById(@PathVariable("id") int id) {
        CompanyDTO company = companyService.getCompanyById(id);
        return ResponseEntity.ok(new ApiResponse<>(company));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyDTO>> createCompany(@RequestBody Company company) {
        CompanyDTO created = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(created));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CompanyDTO>>> getAll() {
        List<CompanyDTO> companies = companyService.getAllCompanies();

        return ResponseEntity.ok(new ApiResponse<>(companies));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyDTO>> updateCompany(
            @PathVariable("id") int id,
            @RequestBody Company updateCompany) {
        CompanyDTO existing = companyService.updateCompany(id, updateCompany);
        return ResponseEntity.ok(new ApiResponse<>(existing));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyDTO>> patchCompany(
            @PathVariable("id") int id,
            @RequestBody Company updateCompany
    ) {
        CompanyDTO company = companyService.patchCompany(id, updateCompany);
        return ResponseEntity.ok(new ApiResponse<>(company));
    }
}
