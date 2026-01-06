package org.example.dto.mapper;

import org.example.dto.TourDTO;
import org.example.model.Tour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourMapper {
    TourDTO tourToTourDTO(Tour tour);
}
