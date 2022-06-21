package com.daniele.nfe.errors;


import lombok.Builder;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class RestResponseExceptionHandler {


        @ExceptionHandler(NotFoundException.class)
        @ResponseStatus(value= HttpStatus.NOT_FOUND)
        @ResponseBody
        public Message requestHandlingNoHandlerFound(NotFoundException exception ) {
            return Message.builder().shortMessagem("Not Found").detail(Map.of("conteúdo",exception.getMessage()) ).build();
        }

        @ExceptionHandler(BadRequestException.class)
        @ResponseStatus(value= HttpStatus.BAD_REQUEST)
        @ResponseBody
        public Message requestHandlingNoHandlerFound(BadRequestException exception ) {
                return Message.builder().shortMessagem("Bad Request").detail(Map.of("content",exception.getMessage()) ).build();
        }

        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseStatus(value= HttpStatus.BAD_REQUEST)
        @ResponseBody
        public Message requestHandlingNoHandlerFound(IllegalArgumentException exception ) {
                return Message.builder().shortMessagem("Bad Request").detail(Map.of("content",exception.getMessage()) ).build();
        }


        @ExceptionHandler(DataIntegrityViolationException.class)
        @ResponseStatus(value= HttpStatus.CONFLICT)
        @ResponseBody
        public Message requestHandlingNoHandlerFound(DataIntegrityViolationException exception ) {
                return Message.builder().shortMessagem("Cannot do this operation. Data has a relationship with another one.")
                        .detail(Map.of("content",exception.getMessage()) ).build();
        }

        @ExceptionHandler(ConstraintViolationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        Message onConstraintValidationException(
                ConstraintViolationException e) {
                Message error = Message.builder().shortMessagem("Bad Request").detail(Map.of() ).build();
                for (ConstraintViolation violation : e.getConstraintViolations()) {
                        error.addDetail(violation.getPropertyPath().toString(), violation.getMessage());
                }
                return error;
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        Message onMethodArgumentNotValidException(
                MethodArgumentNotValidException e) {
                Message error = Message.builder().shortMessagem("Bad Request").build();
                for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
                        error.addDetail(fieldError.getField(), fieldError.getDefaultMessage());
                }
                return error;
        }

}
@Builder
@Data
class Message{
        private String shortMessagem;
        private Map<String, Object> detail;

        public void addDetail(String key, Object value){
                this.detail = Optional.ofNullable(this.detail).orElseGet(HashMap::new);
                this.detail.put(key,value);

        }

}