    package org.example.controller;

    import org.example.model.Company;
    import org.example.repository.CompanyRepository;
    import org.example.service.CompanyService;
    import org.springframework.web.bind.annotation.*;

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



    }
