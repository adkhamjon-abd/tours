package org.example.repository;

import org.example.model.Company;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CompanyRepository {

    private final Map<Integer, Company> companies = new HashMap<>();
    private int nextId = 6;

    public CompanyRepository(){
        companies.put(1, new Company(1, "Green Travel"));
        companies.put(2, new Company(2, "World Tours"));
        companies.put(3, new Company(3, "Samo Travel"));
        companies.put(4, new Company(4, "Holiday Abroad"));
        companies.put(5, new Company(5, "Afsona Travel"));
    }
    public String save(Company company){
        company.setId(nextId);
        companies.put(nextId, company);
        nextId++;
        return "Company: " + company.getName() + " created";
    }

    public Company findById(int id){
        return companies.get(id);
    }

}
