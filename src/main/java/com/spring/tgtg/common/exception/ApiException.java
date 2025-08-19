package com.spring.tgtg.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    //GlobalErrorCode 를 보유하고, super(errorCode.getMessage()) 로 상위 예외 메세지도 셋팅
    private final GlobalErrorCode errorCode;

    public ApiException(GlobalErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
