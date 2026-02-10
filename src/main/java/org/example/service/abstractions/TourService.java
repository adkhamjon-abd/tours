package org.example.service.abstractions;

import org.example.dto.request.CreateTourRequest;
import org.example.dto.request.UpdateTourRequest;
import org.example.dto.response.TourResponse;

import java.util.List;

public interface TourService {
    TourResponse createTour(CreateTourRequest tour);
    TourResponse getById(int id);
    List<TourResponse> getAll();
    void deleteTour(int id);
    TourResponse updateTour(int id, UpdateTourRequest updateTour);
    TourResponse patchTour(int id, UpdateTourRequest updateTour);
}
