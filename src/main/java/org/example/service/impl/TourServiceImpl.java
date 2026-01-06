package org.example.service.impl;

import org.example.dto.TourDTO;
import org.example.dto.mapper.TourMapper;
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

    public TourDTO createTour(Tour tour) {
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
        return tourMapper.tourToTourDTO(tourRepository.save(tour));
    }

    public TourDTO getById(int id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );

        tour.setViewCount(tour.getViewCount() + 1);

        return tourMapper.tourToTourDTO(tour);
    }

    public List<TourDTO> getAll() {
        List<TourDTO> tours = tourRepository.findAll().stream().map(tourMapper::tourToTourDTO).toList();
        return tours;
    }

    public void deleteTour(int id) {
        tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );
        tourRepository.deleteTourById(id);
    }

    public TourDTO updateTour(int id, Tour updateTour) {
        Tour tour = tourRepository.findById(id).orElseThrow(() ->
                new TourNotFoundException("Tour with such id does not exist")
        );

        tour.setName(updateTour.getName());
        tour.setCompanyId(updateTour.getCompanyId());
        tour.setViewCount(updateTour.getViewCount());
        return tourMapper.tourToTourDTO(tour);
    }

    public TourDTO patchTour(int id, Tour updateTour) {
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
        return tourMapper.tourToTourDTO(existingTour);
    }
}
