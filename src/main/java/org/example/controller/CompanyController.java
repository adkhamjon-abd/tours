    package org.example.controller;

    import org.example.model.Company;
    import org.example.repository.CompanyRepository;
    import org.springframework.web.bind.annotation.*;

    @RestController()
    @RequestMapping("/companies")
    public class CompanyController {

        private final CompanyRepository companyRepository;

        public CompanyController(CompanyRepository companyRepository){
            this.companyRepository = companyRepository;
        }

        @GetMapping("/{id}")
        public String getCompanyById(@PathVariable("id") int id){
            Company company = companyRepository.findById(id);

            if (company == null){ return "Company not found"; }

            return "Company Name: " + company.getName() + "\n" +
                   "Company ID: " + company.getId();
        }

        @PostMapping
        public String createCompany(@RequestBody Company company){
            return companyRepository.save(company);
        }



    }
