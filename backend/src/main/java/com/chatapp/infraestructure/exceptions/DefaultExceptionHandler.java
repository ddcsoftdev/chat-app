package com.chatapp.infraestructure.exceptions;

import com.chatapp.infraestructure.exceptions.types.DuplicateResourceException;
import com.chatapp.infraestructure.exceptions.types.RequestValidationException;
import com.chatapp.infraestructure.exceptions.types.ResourceNotFoundException;
import com.chatapp.infraestructure.exceptions.types.UnauthorizedUserException;
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
    public ResponseEntity<ExceptionResponseDTO> handleException (DuplicateResourceException e,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response){
        ExceptionResponseDTO exResponse = new ExceptionResponseDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException (ResourceNotFoundException e,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response){
        ExceptionResponseDTO exResponse = new ExceptionResponseDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException (UnauthorizedUserException e,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response){
        ExceptionResponseDTO exResponse = new ExceptionResponseDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException (RequestValidationException e,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response){
        ExceptionResponseDTO exResponse = new ExceptionResponseDTO(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exResponse, HttpStatus.BAD_REQUEST);
    }
}
