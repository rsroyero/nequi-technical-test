package com.example.nequi_technical_test.service;

import com.example.nequi_technical_test.model.Branch;
import com.example.nequi_technical_test.model.Franchise;
import com.example.nequi_technical_test.model.Product;
import com.example.nequi_technical_test.model.ProductDTO;
import com.example.nequi_technical_test.repository.BranchRepository;
import com.example.nequi_technical_test.repository.FranchiseRepository;
import com.example.nequi_technical_test.repository.ProductRepository;
import com.example.nequi_technical_test.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    public Franchise addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Branch addBranch(Long franchiseId, Branch branch) {
        Optional<Franchise> franchise = franchiseRepository.findById(franchiseId);
        if (franchise.isPresent()) {
            branch.setFranchise(franchise.get());
            return branchRepository.save(branch);
        }
        return null;
    }

    public Product addProduct(Long branchId, Product product) {
        Optional<Branch> branch = branchRepository.findById(branchId);
        if (branch.isPresent()) {
            product.setBranch(branch.get());
            return productRepository.save(product);
        }
        return null;
    }

    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateStock(Long productId, Product product) {
        Product productResult = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productResult.setName(product.getName());
        productResult.setStock(product.getStock());

        return productRepository.save(productResult);
    }

    public List<ProductDTO> getTopStockProductsByBranch(Long franchiseId) {
        List<Branch> branches = branchRepository.findByFranchiseId(franchiseId);

        return branches.stream()
                .map(branch -> {
                    List<Product> products = productRepository.findByBranchId(branch.getId());
                    Optional<Product> topProduct = products.stream()
                            .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()));

                    return topProduct.map(product -> new ProductDTO(
                            product.getName(),
                            product.getStock(),
                            branch.getName()
                    )).orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Optional<Franchise> getFranchisesById(Long franchiseId){
        return  franchiseRepository.findById(franchiseId);
    }
}
