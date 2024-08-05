package com.example.nequi_technical_test.repository;

import com.example.nequi_technical_test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBranchId(Long branchId);
}