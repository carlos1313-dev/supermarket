/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.dto;

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
public class ProductInSaleDTO {
    private Long id;
    private int amount;
    private double unitPrice;

    private String productName;
    private double subtotal;
    
    //private long saleId;
}
