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
}
