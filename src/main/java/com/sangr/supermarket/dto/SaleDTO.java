/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sangr.supermarket.entity.Branch;
import com.sangr.supermarket.entity.ProductInSale;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author sangr
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class SaleDTO {
    private Long id;
    private LocalDate date;
    private String state;
    
    private double total;
    
    private List<ProductInSaleDTO> prodInSale;
    private Long branchId;
}
