/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sangr.supermarket.service;

import com.sangr.supermarket.dto.SaleDTO;
import java.util.List;

/**
 *
 * @author sangr
 */
public interface ISale {
    List<SaleDTO> getSales (); //Get
    SaleDTO getSale(Long id);
    SaleDTO createSaleDTO (SaleDTO productDTO); //Create
    SaleDTO updateSaleDTO (SaleDTO productDTO, Long id); //Update
    void deleteSaleDTO(Long id); //Delete
}
