package com.example.nequi_technical_test.repository;

import com.example.nequi_technical_test.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByFranchiseId(Long franchiseId);
}
