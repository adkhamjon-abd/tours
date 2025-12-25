package org.example.repository;

import org.example.model.Tour;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TourRepository {
    private final Map<Integer, Tour> tours = new HashMap<>();
    private int nextId = 6;
    public TourRepository() {
        tours.put(1, new Tour(1, "Dubai", 1, 0));
        tours.put(2, new Tour(2, "Italy", 2, 0));
        tours.put(3, new Tour(3, "Turikye", 3, 0));
        tours.put(4, new Tour(4, "France", 4, 0));
        tours.put(5, new Tour(5, "Malaysia", 5, 0));
    }

    public Tour save(Tour tour){
        tour.setId(nextId++);
        tours.put(tour.getId(), tour);
        return tour;
    }

    public Optional<Tour> findById(int id){
        return tours.values().stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public List<Tour> findByCompanyId(int companyId){
        return tours.values().stream()
                .filter(tour -> tour.getCompanyId() == companyId)
                .collect(Collectors.toList());
    }

    public List<Tour> findAll(){
        return new ArrayList<>(tours.values());
    }

    public void deleteTourById(int id) {
        tours.entrySet().removeIf(entry -> entry.getValue().getId() == id);
    }

    public void update(Tour tour){
        tours.put(tour.getId(), tour);
    }
}
