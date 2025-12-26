package org.example.service.abstractions;

import org.example.model.Tour;

import java.util.List;

public interface TourService {
    Tour createTour(Tour tour);
    Tour getById(int id);
    List<Tour> getAll();
    void deleteTour(int id);
    Tour updateTour(int id, Tour updateTour);
    Tour patchTour(int id, Tour updateTour);
}
