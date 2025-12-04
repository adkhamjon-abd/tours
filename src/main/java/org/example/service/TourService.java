package org.example.service;

import org.example.model.Tour;
import org.example.repository.CompanyRepository;
import org.example.repository.TourRepository;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final CompanyRepository companyRepository;

    public TourService(TourRepository tourRepository, CompanyRepository companyRepository){
        this.tourRepository = tourRepository;
        this.companyRepository = companyRepository;
    }

    public String createTour(Tour tour) {
        if (companyRepository.findById(tour.getCompanyId()) == null) {
            return "Company does not exist";
        }
        return tourRepository.save(tour);
    }

    public String getById(int id) {
        Tour tour = tourRepository.findById(id);
        return "Tour Name: " + tour.getName() + " The view count is: " + tour.getViewCount();
    }

    public String getAll() {
        return tourRepository.findAll().toString();
    }

    public String deleteTour(int id) {
        return tourRepository.deleteTourById(id);
    }
}
