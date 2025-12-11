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
            Company company = companyService.getCompanyById(id);

            if (company == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company with such id does not exist");
            }

            return ResponseEntity.ok(company);
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
            boolean isDeleted = companyService.deleteCompany(id);

            if (!isDeleted){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company with such id does not exist");
            }
            return ResponseEntity.noContent().build();
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

        @PatchMapping("/{id}")
        public ResponseEntity<Company> patchCompany(
                @PathVariable("id") int id,
                @RequestBody Company updateCompany
        ) {
            Company company = companyService.patchCompany(id, updateCompany);
            if (company == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updateCompany);
        }
    }
