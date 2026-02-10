package org.example.service.impl;

import org.example.dto.mapper.TourMapper;
import org.example.dto.request.CreateTourRequest;
import org.example.dto.request.UpdateTourRequest;
import org.example.dto.response.TourResponse;
import org.example.exception.CompanyNotFoundException;
import org.example.exception.TourAlreadyExistsException;
import org.example.exception.TourNotFoundException;
import org.example.model.Tour;
import org.example.repository.CompanyRepository;
import org.example.repository.TourRepository;
import org.example.service.abstractions.TourService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final CompanyRepository companyRepository;
    private final TourMapper tourMapper;

    public TourServiceImpl(TourRepository tourRepository, CompanyRepository companyRepository, TourMapper tourMapper){
        this.tourRepository = tourRepository;
        this.companyRepository = companyRepository;
        this.tourMapper = tourMapper;
    }

    public TourResponse createTour(CreateTourRequest tourRequest) {
        Tour tour = tourMapper.toEntity(tourRequest);
        tourRepository.findById(tour.getId())
                .ifPresent(t -> {
                    throw new TourAlreadyExistsException(
                            "Tour with this id already exists"
                    );
                });


        companyRepository.findById(tour.getCompanyId()).orElseThrow(
                () -> new CompanyNotFoundException("Company with such id does not exist")
        );

        //companyid and id
        return tourMapper.toResponse(tourRepository.save(tour));
    }

    public TourResponse getById(int id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );

        tour.setViewCount(tour.getViewCount() + 1);
        tourRepository.update(tour);
        return tourMapper.toResponse(tour);
    }

    public List<TourResponse> getAll() {
        List<TourResponse> tours = tourRepository.findAll().stream().map(tourMapper::toResponse).toList();
        return tours;
    }

    public void deleteTour(int id) {
        tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );
        tourRepository.deleteTourById(id);
    }

    public TourResponse updateTour(int id, UpdateTourRequest updateTour) {
        Tour tour = tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );

        tour.setName(updateTour.getName());
        tour.setCompanyId(updateTour.getCompanyId());
        tour.setViewCount(updateTour.getViewCount());

        tourRepository.update(tour);
        return tourMapper.toResponse(tour);
    }

    public TourResponse patchTour(int id, UpdateTourRequest updateTour) {
        Tour existingTour = tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );
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
        return tourMapper.toResponse(existingTour);
    }
}
