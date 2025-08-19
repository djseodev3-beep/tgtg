package com.spring.tgtg.reservation.domain;

public enum ReservationStatus {
    RESERVED("예약")
    , PICKED_UP("픽업")
    , CANCELED("취소");

    private final String label;

    ReservationStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
