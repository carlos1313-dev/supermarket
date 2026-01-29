/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.mapper;

import com.sangr.supermarket.dto.*;
import com.sangr.supermarket.entity.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author sangr
 */

public class Mapper {

    /* =======================
       BRANCH
       ======================= */

    public static BranchDTO toBranchDTO(Branch branch) {
        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .build();
    }

    public static Branch toBranchEntity(BranchDTO dto) {
        return Branch.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
    }

    /* =======================
       PRODUCT
       ======================= */

    public static ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .amount(product.getAmount())
                .build();
    }

    public static Product toProductEntity(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .amount(dto.getAmount())
                .build();
    }

    /* =======================
       PRODUCT IN SALE
       ======================= */

    public static ProductInSaleDTO toProductInSaleDTO(ProductInSale pis) {
        return ProductInSaleDTO.builder()
                .id(pis.getId())
                .amount(pis.getAmount())
                .unitPrice(pis.getUnitPrice())
                .productName(pis.getProduct().getName())
                .subtotal(Double.valueOf(pis.getUnitPrice()) * (pis.getAmount()))
                //.saleId(pis.getSale().getId())
                .build();
    }

    public static ProductInSale toProductInSaleEntity(ProductInSaleDTO dto, Product product, Sale sale) {
        return ProductInSale.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .unitPrice(dto.getUnitPrice())
                .product(product)
                .sale(sale)
                .build();
    }

    /* =======================
       SALE
       ======================= */

    public static SaleDTO toSaleDTO(Sale sale) {
        
        List<ProductInSaleDTO> productList = sale.getProducts().stream()
            .map(Mapper::toProductInSaleDTO)
            .collect(Collectors.toList());
    
    // Recalcular para estar 100% seguro
    double calculatedTotal = productList.stream()
            .mapToDouble(ProductInSaleDTO::getSubtotal)
            .sum();
    
        return SaleDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .state(sale.getState())
                .total(calculatedTotal)
                .branchId(sale.getBranch().getId())
                .prodInSale(productList)
                .build();
    }

    public static Sale toSaleEntity(SaleDTO dto, Branch branch) {
        return Sale.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .state(dto.getState())
                .total(dto.getTotal())
                .branch(branch)
                .build();
    }
}
