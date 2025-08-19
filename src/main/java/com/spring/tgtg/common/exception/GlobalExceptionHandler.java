package com.spring.tgtg.common.exception;

import com.spring.tgtg.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //RestController 에서 발생하는 예외를 가로채 표준 Json 구조의 ApiResponse로 변환하여 Http 구조로 반환.

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApi(ApiException e){
        var code = e.getErrorCode();
        return ResponseEntity.status(code.getStatus()).body(ApiResponse.error(code.getStatus(), code.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException  e){
        //e.getMessage() 를 그대로 내려 잠재적으로 갈고 난해한다. 필드별 메세지를 묶어 깔끔히 가공 필요.
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " +fe.getDefaultMessage())
                .collect(Collectors.joining(","));
        return ResponseEntity.badRequest().body(ApiResponse.error(400,msg));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidation(ConstraintViolationException e){
        String msg = e.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(","));
        return ResponseEntity.badRequest().body(ApiResponse.error(400,msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleEtc(Exception e) {
        log.error("Unhandled exception",e);
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(500, "서버 오류가 발생했습니다."));
    }

}
