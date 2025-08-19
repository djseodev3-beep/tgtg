package com.spring.tgtg.user.domain;

public enum UserRole {
    CUSTOMER("구매자"),
    STORE_OWNER("사장님"),
    ADMIN("관리자");

    private final String label;
    UserRole(String label) {
        this.label = label;
    }
    public String label() {
        return label;
    }
}
