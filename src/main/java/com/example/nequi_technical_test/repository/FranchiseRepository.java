package com.example.nequi_technical_test.repository;

import com.example.nequi_technical_test.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
