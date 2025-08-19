package com.spring.tgtg.product.domain;

import com.spring.tgtg.common.jpa.BaseTimeEntity;
import com.spring.tgtg.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer originalPrice;

    @Column(nullable = false)
    private Integer discountPrice;

    @Column(nullable = false)
    private Integer quantity;

    private LocalDateTime pickupStartTime;
    private LocalDateTime pickupEndTime;
    private LocalDate availableDate;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;


    public void decreaseQuantity(int count) {
        this.quantity -= count;
    }
    public boolean isAvailable(){
        return status == ProductStatus.AVAILABLE;
    }
}
