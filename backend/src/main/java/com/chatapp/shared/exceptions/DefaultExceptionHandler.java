package com.chatapp.shared.exceptions;

import com.chatapp.shared.exceptions.types.DuplicateResourceException;
import com.chatapp.shared.exceptions.types.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiErrorDTO> handleException (DuplicateResourceException e,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response){
        ApiErrorDTO apiError = new ApiErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleException (ResourceNotFoundException e,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response){
        ApiErrorDTO apiError = new ApiErrorDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
