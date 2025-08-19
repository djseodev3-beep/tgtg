package com.spring.tgtg.reservation.domain;

import com.spring.tgtg.common.jpa.BaseTimeEntity;
import com.spring.tgtg.product.domain.Product;
import com.spring.tgtg.store.domain.Store;
import com.spring.tgtg.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resrvations")
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    private LocalDateTime pickedUpAt;

    public void pickup() {
        this.status = ReservationStatus.PICKED_UP;
        this.pickedUpAt = LocalDateTime.now();
    }
}
