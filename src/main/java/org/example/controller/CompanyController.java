    package org.example.controller;

    import org.example.model.Company;
    import org.example.repository.CompanyRepository;
    import org.example.service.CompanyService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController()
    @RequestMapping("/companies")
    public class CompanyController {

        private final CompanyRepository companyRepository;
        private final CompanyService companyService;

        public CompanyController(CompanyRepository companyRepository, CompanyService companyService){
            this.companyRepository = companyRepository;
            this.companyService = companyService;
        }

        @GetMapping("/{id}")
        public String getCompanyById(@PathVariable("id") int id){
            return companyService.getCompanyById(id);
        }

        @PostMapping
        public String createCompany(@RequestBody Company company){
            return companyService.createCompany(company);
        }

        @GetMapping()
        public List<Company> getAll(){
            return companyService.getAllCompanies();
        }

        @DeleteMapping("/{id}")
        public String deleteCompany(@PathVariable("id") int id){
            return companyService.deleteCompany(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Company> updateCompany(
                @PathVariable("id") int id,
                @RequestBody Company updateCompany) {
            Company company = companyService.updateCompany(id, updateCompany);

            if (company == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(company);
        }
    }
