/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.service;

import com.sangr.supermarket.dto.ProductInSaleDTO;
import com.sangr.supermarket.dto.SaleDTO;
import com.sangr.supermarket.entity.Branch;
import com.sangr.supermarket.entity.Product;
import com.sangr.supermarket.entity.ProductInSale;
import com.sangr.supermarket.entity.Sale;
import com.sangr.supermarket.exception.NotFoundException;
import com.sangr.supermarket.mapper.Mapper;
import com.sangr.supermarket.repository.BranchRepository;
import com.sangr.supermarket.repository.ProductRepository;
import com.sangr.supermarket.repository.SaleRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sangr
 */
@Service
@Transactional
public class SaleService implements ISale {
    double total = 0;

    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private BranchRepository branchRepo;

    @Override
    public List<SaleDTO> getSales() {
        List<Sale> sales = saleRepo.findAll();
        List<SaleDTO> salesDTO = new ArrayList<>();
        SaleDTO dto;

        for (Sale s : sales) {
            dto = Mapper.toSaleDTO(s);
            salesDTO.add(dto);
        }

        return salesDTO;

    }
    @Override
    public SaleDTO getSale(Long id){
        Sale sale = saleRepo.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la venta"));
        SaleDTO saleDTO = Mapper.toSaleDTO(sale);
        return saleDTO;
    }

    @Override
    public SaleDTO createSaleDTO(SaleDTO saleDTO) {
        if (saleDTO == null) {
            throw new NotFoundException("No se encontró la venta");
        }
        if(saleDTO.getBranchId() == null) throw new NotFoundException("No se encontró la sucursal");
        if (saleDTO.getProdInSale() == null) {
            throw new NotFoundException("No se encontró el producto en la venta");
        }

        //Find branch
        Branch branch = branchRepo.findById(saleDTO.getBranchId()).orElseThrow(() -> new NotFoundException("No se encontró la sucursal"));

        //Create sale
        Sale sale = new Sale();

        sale.setState(saleDTO.getState());
        sale.setDate(saleDTO.getDate());
        sale.setBranch(branch);
        //sale.setTotal(saleDTO.getTotal());

        List<ProductInSale> products = new ArrayList<>();

        for (ProductInSaleDTO productInSaleDTO : saleDTO.getProdInSale()) {
            Product prod = prodRepo.findByName(productInSaleDTO.getProductName()).orElseThrow(() -> new NotFoundException("No se encontró el producto"));

            ProductInSale prodInSale = new ProductInSale();
            prodInSale.setProduct(prod);
            prodInSale.setSale(sale);
            prodInSale.setAmount(productInSaleDTO.getAmount());
            prodInSale.setUnitPrice(productInSaleDTO.getUnitPrice());

            products.add(prodInSale);
            total = total += (productInSaleDTO.getAmount()*productInSaleDTO.getUnitPrice());
        }
        sale.setTotal(total);
        sale.setProducts(products);

        //Save sale
        saleRepo.save(sale);

        //Mapear
        SaleDTO finalSaleDTO = Mapper.toSaleDTO(sale);

        return finalSaleDTO;

    }

    @Override
    public SaleDTO updateSaleDTO(SaleDTO saleDTO, Long id) {

        Sale sale = saleRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la venta con id: " + id));

        if (saleDTO.getDate() != null) {
            sale.setDate(saleDTO.getDate());
        }

        if (saleDTO.getState() != null) {
            sale.setState(saleDTO.getState());
        }

        if (saleDTO.getBranchId() != null) {
            Branch branch = branchRepo.findById(saleDTO.getBranchId())
                    .orElseThrow(() -> new NotFoundException(
                    "No se encontró la sucursal con id: " + saleDTO.getBranchId()
            ));
            sale.setBranch(branch);
        }

        Sale updatedSale = saleRepo.save(sale);

        return Mapper.toSaleDTO(updatedSale);
    }

    @Override
    public void deleteSaleDTO(Long id) {
        if (!saleRepo.existsById(id)) {
            throw new NotFoundException("No se encontró la venta con id: " + id);
        }
        saleRepo.deleteById(id);
    }

}
