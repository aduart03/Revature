package com.test1.lecture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.*;

import com.test1.lecture.exceptions.RecordNotFoundException ;

import io.micrometer.core.ipc.http.HttpSender.Response;


@RestControllerAdvice 
public class StudentExceptionHandler {

    @ExceptionHandler (RecordNotFoundException .class)
    public ResponseEntity<StudentErrorResponse> handleRecordNotFoundException (
            RecordNotFoundException  e){
                StudentErrorResponse error = new StudentErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage(),
                    System.currentTimeMillis()); 

                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<StudentErrorResponse> handleValidationException( MethodArgumentNotValidException e){

        String details = e.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(";"));
         StudentErrorResponse error = new StudentErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    details,
                    System.currentTimeMillis()); 

                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler (MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StudentErrorResponse> handleMethodArgsException(
      MethodArgumentTypeMismatchException e
    ){

        StudentErrorResponse error = new StudentErrorResponse(
            HttpStatus.BAD_REQUEST.value(), "The ID provided was not valid. Please only supply an integer.", System.currentTimeMillis());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);


    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<StudentErrorResponse> handleGenericException(Exception e){

        StudentErrorResponse error = new StudentErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An internal error occurred.",
                    System.currentTimeMillis()); 

                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
