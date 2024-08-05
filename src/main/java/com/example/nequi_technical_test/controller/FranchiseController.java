package com.example.nequi_technical_test.controller;

import com.example.nequi_technical_test.model.Branch;
import com.example.nequi_technical_test.model.Franchise;
import com.example.nequi_technical_test.model.Product;
import com.example.nequi_technical_test.model.ProductDTO;
import com.example.nequi_technical_test.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {
    @Autowired
    private FranchiseService franchiseService;

    @GetMapping("/id/{id}")
    public Optional<Franchise> getId(@PathVariable("id") final Integer id) {
        return franchiseService.getFranchisesById(Long.valueOf(id));
    }

    @PostMapping
    public Franchise addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise);
    }

    @PostMapping("/{franchiseId}/branches")
    public Branch addBranch(@PathVariable Long franchiseId, @RequestBody Branch branch) {
        return franchiseService.addBranch(franchiseId, branch);
    }

    @PostMapping("/branches/{branchId}/products")
    public Product addProduct(@PathVariable Long branchId, @RequestBody Product product) {
        return franchiseService.addProduct(branchId, product);
    }

    @DeleteMapping("/products/{productId}")
    public void removeProduct(@PathVariable Long productId) {
        franchiseService.removeProduct(productId);
    }

    @PutMapping("/products/{productId}")
    public Product updateStock(@PathVariable Long productId, @RequestBody Product product) {
        return franchiseService.updateStock(productId, product);
    }

    @GetMapping("/{franchiseId}/top-products")
    public List<ProductDTO> getTopStockProductsByBranch(@PathVariable Long franchiseId) {
        return franchiseService.getTopStockProductsByBranch(franchiseId);
    }
}
