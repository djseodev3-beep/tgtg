package com.spring.tgtg.review.repository;

import com.spring.tgtg.review.domain.Review;
import com.spring.tgtg.store.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByStore(Store store, Pageable pageable);
}
