    package org.example.controller;

    import org.example.model.Company;
    import org.example.repository.CompanyRepository;
    import org.example.service.CompanyService;
    import org.springframework.http.HttpStatus;
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
        public ResponseEntity<?> getCompanyById(@PathVariable("id") int id){
            return companyService.getCompanyById(id);
        }

        @PostMapping
        public ResponseEntity<?> createCompany(@RequestBody Company company){
            Company created = companyService.createCompany(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }

        @GetMapping()
        public ResponseEntity<List<Company>> getAll(){
            List<Company> companies = companyService.getAllCompanies();

            return ResponseEntity.ok(companies);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCompany(@PathVariable("id") int id){
            return companyService.deleteCompany(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateCompany(
                @PathVariable("id") int id,
                @RequestBody Company updateCompany) {
            return companyService.updateCompany(id, updateCompany);
        }

        @PatchMapping("/{id}")
        public ResponseEntity<?> patchCompany(
                @PathVariable("id") int id,
                @RequestBody Company updateCompany
        ) {
            return companyService.patchCompany(id, updateCompany);
        }
    }
