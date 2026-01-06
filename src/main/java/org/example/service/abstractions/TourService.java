package org.example.service.abstractions;

import org.example.dto.TourDTO;
import org.example.model.Tour;

import java.util.List;

public interface TourService {
    TourDTO createTour(Tour tour);
    TourDTO getById(int id);
    List<TourDTO> getAll();
    void deleteTour(int id);
    TourDTO updateTour(int id, Tour updateTour);
    TourDTO patchTour(int id, Tour updateTour);
}
