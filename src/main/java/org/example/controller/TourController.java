package org.example.controller;

import org.example.model.Tour;
import org.example.repository.CompanyRepository;
import org.example.repository.TourRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourRepository tourRepository;
    private final CompanyRepository companyRepository;

    public TourController(TourRepository tourRepository, CompanyRepository companyRepository){
        this.tourRepository = tourRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public String createTour(@RequestBody Tour tour){
        if (companyRepository.findById(tour.getCompanyId()) == null) {
            return "Company does not exist";
        }
        return tourRepository.save(tour);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id){
        return "Company Name: " + tourRepository.findById(id).getName();
    }

    @GetMapping
    public String getAll(){
        return tourRepository.findAll().toString();
    }
}
