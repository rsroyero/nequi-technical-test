package com.example.nequi_technical_test.model;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class ProductDTO {
    private String productName;
    private int stock;
    private String branchName;
}
