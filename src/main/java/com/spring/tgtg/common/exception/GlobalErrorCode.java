package com.spring.tgtg.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode {

    INVALID_REQUEST(400, "잘못된 요청입니다."),
    UNAUTHORIZED(401, "인증이 필요합니다."),
    FORBIDDEN(403, "접근 권한이 없습니다."),
    NOT_FOUND_USER(404, "사용자를 찾을 수 없습니다."),
    NOT_FOUND_STORE(404, "매장을 찾을 수 없습니다."),
    NOT_FOUND_PRODUCT(404, "상품을 찾을 수 없습니다."),
    NOT_FOUND_RESERVATION(404, "예약을 찾을 수 없습니다."),
    CONFLICT(409, "충돌이 발생했습니다."),
    OUT_OF_STOCK(409, "재고가 부족합니다."),
    REVIEW_NOT_ALLOWED(409, "리뷰를 작성할 수 없습니다."),
    SERVER_ERROR(500, "서버 오류가 발생했습니다.");


    private final int status;
    private final String message;
}
