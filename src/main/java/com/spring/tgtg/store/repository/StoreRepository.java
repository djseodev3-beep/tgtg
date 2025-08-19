package com.spring.tgtg.store.repository;

import com.spring.tgtg.store.domain.Store;
import com.spring.tgtg.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByOwner(User owner);

}
