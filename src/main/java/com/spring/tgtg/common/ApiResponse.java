package com.spring.tgtg.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Getter
@Builder(toBuilder = true) // ← 기존 Builder에 toBuilder 활성화
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private OffsetDateTime timestamp;


    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(200)
                .message("OK")
                .data(data)
                .timestamp(OffsetDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> created(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(201)
                .message("CREATED")
                .data(data)
                .timestamp(OffsetDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .timestamp(OffsetDateTime.now())
                .build();
    }

    //Fluent Interface , Method Chaining
    public ApiResponse<T> withMessage(String message) {
        return this.toBuilder().message(message).build();
    }
    public ApiResponse<T> withCode(int code) {
        return this.toBuilder().code(code).build();
    }

    public ApiResponse<T> withData(T data) {
        return this.toBuilder().data(data).build();
    }

    public ApiResponse<T> withTimestamp(OffsetDateTime ts) {
        return this.toBuilder().timestamp(ts).build();
    }
}
