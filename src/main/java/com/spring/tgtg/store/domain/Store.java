package com.spring.tgtg.store.domain;

import com.spring.tgtg.common.jpa.BaseTimeEntity;
import com.spring.tgtg.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stores")
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 255)
    private String address;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 500)
    private String description;

    private LocalTime openTime;
    private LocalTime closeTime;

    private Double latitude;    //WGS84
    private Double longitude;   //WGS84



}
