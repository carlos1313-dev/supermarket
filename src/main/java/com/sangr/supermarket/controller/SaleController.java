/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.controller;

import com.sangr.supermarket.dto.SaleDTO;
import com.sangr.supermarket.service.ISale;
import com.sangr.supermarket.service.SaleService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sangr
 */
@RestController
@RequestMapping("/api/sales")
public class SaleController {
    
    @Autowired
    private ISale saleSer;
    
    @GetMapping 
    public ResponseEntity<List<SaleDTO>> getAllSales (){
        List<SaleDTO> sales = saleSer.getSales();
        return ResponseEntity.ok(sales);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<SaleDTO> getSale (@PathVariable Long id){
        SaleDTO sale = saleSer.getSale(id);
        return ResponseEntity.ok(sale);
    }
    
    @PostMapping()
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO){
        SaleDTO created = saleSer.createSaleDTO(saleDTO);
        return ResponseEntity.created(URI.create("/api/sales" + created.getId())).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@RequestBody SaleDTO saleDTO, @PathVariable Long id){
        SaleDTO updated = saleSer.updateSaleDTO(saleDTO, id);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleSer.deleteSaleDTO(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
