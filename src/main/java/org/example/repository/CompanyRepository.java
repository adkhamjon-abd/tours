package org.example.repository;

import org.example.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        company.setId(nextId++);
        companies.put(company.getId(), company);
        return "Company: " + company.getName() + " created";
    }

    public Company findById(int id){
        return companies.get(id);
    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    public String deleteById(int id) {
        companies.entrySet().removeIf(entry -> entry.getValue().getId() == id);
        return "Company deleted";
    }

    public void update(Company company) {
        companies.put(company.getId(), company);
    }
}
