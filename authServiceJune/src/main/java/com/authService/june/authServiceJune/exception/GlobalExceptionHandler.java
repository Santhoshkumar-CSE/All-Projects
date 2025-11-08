package com.authService.june.authServiceJune.exception;

import com.authService.june.authServiceJune.DTO.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> resourceNotFoundException(ResourceNotFoundException ex)
    {
        APIResponse<String> response=new APIResponse<>(ex.getMessage(),404,ex.getFieldName());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));

    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse<?>> apiResponseResponseEntity(APIException e){
        APIResponse<String> response=new APIResponse<>(e.getMessage(),404,null);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> mapResponseEntity(MethodArgumentNotValidException e)
    {
        Map<String,String> response=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err->{
                String message=((FieldError)err).getDefaultMessage();
        String field=((FieldError) err).getField();
        response.put(field,message);});
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> dataIntegrityViolation(DataIntegrityViolationException e){
        Map<String,String> response=new HashMap<>();
        response.put("Error","Data Integrity violated"+e.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
