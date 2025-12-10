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

    public Tour updateTour(int id, Tour updateTour) {
        Tour tour = tourRepository.findById(id);

        if (tour == null){
            return null;
        }

        tour.setName(updateTour.getName());
        tour.setCompanyId(updateTour.getCompanyId());
        tour.setViewCount(updateTour.getViewCount());
        return tour;
    }

    public Tour patchTour(int id, Tour updateTour) {
        Tour existingTour = tourRepository.findById(id);

        if (existingTour == null) return null;

        existingTour.setName(updateTour.getName());
        existingTour.setCompanyId(updateTour.getCompanyId());
        existingTour.setViewCount(updateTour.getViewCount());

        if (updateTour.getName() != null) {
            existingTour.setName(updateTour.getName());
        }

        if (updateTour.getCompanyId() >= 0){
            existingTour.setCompanyId(updateTour.getCompanyId());
        }

        if (updateTour.getViewCount() >= 0){
            existingTour.setViewCount(updateTour.getViewCount());
        }

        tourRepository.update(existingTour);
        return existingTour;
    }
}
