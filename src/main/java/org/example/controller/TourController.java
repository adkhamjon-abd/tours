package org.example.controller;

import org.example.model.Tour;
import org.example.repository.CompanyRepository;
import org.example.repository.TourRepository;
import org.example.service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final CompanyRepository companyRepository;
    private final TourService tourService;

    public TourController(CompanyRepository companyRepository, TourService tourService){
        this.companyRepository = companyRepository;
        this.tourService = tourService;
    }

    @PostMapping
    public String createTour(@RequestBody Tour tour){
        return tourService.createTour(tour);
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id){
        return tourService.getById(id);
    }

    @GetMapping
    public String getAll(){
        return tourService.getAll();
    }

    @DeleteMapping("/{id}")
    public String deleteTour(@PathVariable("id") int id){
        return tourService.deleteTour(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(
            @PathVariable("id") int id,
            @RequestBody Tour updateTour
    ) {
        Tour tour = tourService.updateTour(id, updateTour);

        if (tour == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tour);
    }
}
