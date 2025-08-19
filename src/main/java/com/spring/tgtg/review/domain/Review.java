package com.spring.tgtg.review.domain;

import com.spring.tgtg.common.jpa.BaseTimeEntity;
import com.spring.tgtg.reservation.domain.Reservation;
import com.spring.tgtg.store.domain.Store;
import com.spring.tgtg.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviews")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private int rating; // 1~5

    @Column(nullable = false, length = 500)
    private String content;
}
