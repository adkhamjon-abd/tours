package org.example.dto.mapper;

import org.example.dto.RatingDTO;
import org.example.dto.request.CreateRatingRequest;
import org.example.dto.request.UpdateRatingRequest;
import org.example.dto.response.RatingResponse;
import org.example.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingDTO ratingToRatingDTO(Rating rating);

    Rating toEntity(CreateRatingRequest createRatingRequest);
    Rating toEntity(UpdateRatingRequest updateRatingRequest);

    RatingResponse toResponse(Rating rating);

}
