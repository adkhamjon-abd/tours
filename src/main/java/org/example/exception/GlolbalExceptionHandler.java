package org.example.exception;

import org.example.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlolbalExceptionHandler {
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> bookingNotFoundException(BookingNotFoundException bookingNotFoundException){
        ApiResponse<String> response = new ApiResponse<>(bookingNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BookingAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> bookingAlreadyExistsException(BookingAlreadyExistsException bookingAlreadyExistsException){
        ApiResponse<String> response = new ApiResponse<>(bookingAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidBookingDataException.class)
    public ResponseEntity<ApiResponse<String>> InvalidBookingDataException(InvalidBookingDataException invalidBookingDataException){
        ApiResponse<String> response = new ApiResponse<>(invalidBookingDataException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> userNotFoundException(UserNotFoundException userNotFoundException){
        ApiResponse<String> response = new ApiResponse<>(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TourNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> tourNotFoundException(TourNotFoundException tourNotFoundException){
        ApiResponse<String> response = new ApiResponse<>(tourNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        ApiResponse<String> response = new ApiResponse<>(userAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> companyNotFoundException(CompanyNotFoundException companyNotFoundException){
        ApiResponse<String> response = new ApiResponse<>(companyNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> companyAlreadyExistsException(CompanyAlreadyExistsException companyAlreadyExistsException){
        ApiResponse<String> response = new ApiResponse<>(companyAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TourAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> tourAlreadyExistsException(TourAlreadyExistsException tourAlreadyExistsException){
        ApiResponse<String> response = new ApiResponse<>(tourAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> ratingNotFoundException(RatingNotFoundException ratingNotFoundException){
        ApiResponse<String> response = new ApiResponse<>(ratingNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RatingAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> ratingAlreadyExistsException(RatingAlreadyExistsException ratingAlreadyExistsException){
        ApiResponse<String> response = new ApiResponse<>(ratingAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RatingScoreOutOfRangeException.class)
    public ResponseEntity<ApiResponse<String>> RatingScoreOutOfRangeException(RatingScoreOutOfRangeException ratingScoreOutOfRangeException){
        ApiResponse<String> response = new ApiResponse<>(ratingScoreOutOfRangeException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
