package com.spring.tgtg.product.repository;

import com.spring.tgtg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
