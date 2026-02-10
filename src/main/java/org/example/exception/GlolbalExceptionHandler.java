package org.example.exception;

import org.example.response.ApiResponse;
import org.slf4j.Logger;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlolbalExceptionHandler {

    private final MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(GlolbalExceptionHandler.class);

    public GlolbalExceptionHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    private ResponseEntity<ApiResponse<String>> generalResponse(
            String messageKey,
            HttpStatus httpStatus,
            RuntimeException exception,
            Locale locale,
            Object... args
    ){
        String message = messageSource.getMessage(messageKey, args, locale);
        System.out.println("Resolved message: " + message);

        logger.error(message, exception);

        ApiResponse<String> response = new ApiResponse<>(message);
        return ResponseEntity.status(httpStatus).body(response);
    }
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> bookingNotFoundException(BookingNotFoundException bookingNotFoundException,
                                                                        Locale locale){

        return generalResponse("error.booking.not.found", HttpStatus.NOT_FOUND, bookingNotFoundException, locale, bookingNotFoundException.getId());

    }

    @ExceptionHandler(BookingAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> bookingAlreadyExistsException(BookingAlreadyExistsException bookingAlreadyExistsException,
                                                                             Locale locale){
        return generalResponse("error.booking.already.exists", HttpStatus.CONFLICT, bookingAlreadyExistsException, locale);

    }

    @ExceptionHandler(InvalidBookingDataException.class)
    public ResponseEntity<ApiResponse<String>> InvalidBookingDataException(InvalidBookingDataException invalidBookingDataException,
                                                                           Locale locale){
        return generalResponse("error.booking.invalid.data", HttpStatus.BAD_REQUEST, invalidBookingDataException, locale);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> userNotFoundException(UserNotFoundException userNotFoundException,
                                                                     Locale locale){
        return generalResponse("error.user.not.found", HttpStatus.NOT_FOUND, userNotFoundException, locale, userNotFoundException.getId());

    }

    @ExceptionHandler(TourNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> tourNotFoundException(TourNotFoundException tourNotFoundException,
                                                                     Locale locale){

        return generalResponse("error.tour.not.found", HttpStatus.NOT_FOUND, tourNotFoundException, locale);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException,
                                                                          Locale locale){
        return generalResponse("error.user.already.exists", HttpStatus.CONFLICT, userAlreadyExistsException, locale);

    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> companyNotFoundException(CompanyNotFoundException companyNotFoundException,
                                                                        Locale locale){
        return generalResponse("error.company.not.found", HttpStatus.NOT_FOUND, companyNotFoundException, locale, companyNotFoundException.getId());
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> companyAlreadyExistsException(CompanyAlreadyExistsException companyAlreadyExistsException,
                                                                             Locale locale){
        return generalResponse("error.company.already.exists", HttpStatus.CONFLICT, companyAlreadyExistsException, locale);

    }

    @ExceptionHandler(TourAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> tourAlreadyExistsException(TourAlreadyExistsException tourAlreadyExistsException,
                                                                          Locale locale){
        return generalResponse("error.tour.already.exists", HttpStatus.CONFLICT, tourAlreadyExistsException, locale);

    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> ratingNotFoundException(RatingNotFoundException ratingNotFoundException,
                                                                       Locale locale){
        return generalResponse("error.rating.not.found", HttpStatus.NOT_FOUND, ratingNotFoundException, locale);
    }

    @ExceptionHandler(RatingAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> ratingAlreadyExistsException(RatingAlreadyExistsException ratingAlreadyExistsException,
                                                                            Locale locale){
        return generalResponse("error.rating.already.exists", HttpStatus.CONFLICT, ratingAlreadyExistsException, locale);

    }

    @ExceptionHandler(RatingScoreOutOfRangeException.class)
    public ResponseEntity<ApiResponse<String>> RatingScoreOutOfRangeException(RatingScoreOutOfRangeException ratingScoreOutOfRangeException,
                                                                              Locale locale){
        return generalResponse("error.rating.score.out.of.range", HttpStatus.BAD_REQUEST, ratingScoreOutOfRangeException, locale);
    }
}
