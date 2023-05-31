package com.ssoyoo.blogExperienceSite.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssoyoo.blogExperienceSite.common.util.CustomResponse;
import com.ssoyoo.blogExperienceSite.dto.response.ResponseDto;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handlerHttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        return CustomResponse.validationFail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handlerMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        return CustomResponse.validationFail();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto> methodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception
    ){
        return CustomResponse.validationFail();
    }

}
