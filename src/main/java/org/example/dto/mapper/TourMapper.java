package org.example.dto.mapper;

import org.example.dto.request.CreateTourRequest;
import org.example.dto.request.UpdateTourRequest;
import org.example.dto.response.TourResponse;
import org.example.model.Tour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourMapper {

    Tour toEntity(CreateTourRequest createTourRequest);

    Tour toEntity(UpdateTourRequest updateTourRequest);

    TourResponse toResponse(Tour tour);
}
