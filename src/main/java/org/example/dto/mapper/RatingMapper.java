package org.example.dto.mapper;

import org.example.dto.RatingDTO;
import org.example.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingDTO ratingToRatingDTO(Rating rating);
}
