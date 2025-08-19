package com.spring.tgtg.product.domain;

public enum ProductStatus {

    AVAILABLE("구매 가능"),
    SOLD_OUT("판매 완료"),
    EXPIRED("기간 만료");

    private final String label;

    ProductStatus(String label) {
        this.label = label;
    }

    public String label(){
        return label;
    }

}
