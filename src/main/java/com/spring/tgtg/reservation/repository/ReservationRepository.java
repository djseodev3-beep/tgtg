package com.spring.tgtg.reservation.repository;

import com.spring.tgtg.reservation.domain.Reservation;
import com.spring.tgtg.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStore(Store store);

}
