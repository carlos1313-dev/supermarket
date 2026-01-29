/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
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

@Entity
@Table(name ="sales")
public class Sale {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(length = 50)
    private String state; 
    
    @Column(nullable = false)
    private Double total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
    
    //Not an adicional attribute, only an idication of the relation
    @OneToMany(
        mappedBy = "sale",
        cascade = CascadeType.ALL, //  Propagar operaciones
        orphanRemoval = true, //  Eliminar productos huérfanos
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<ProductInSale> products = new ArrayList<>();

    
    
    
    @PrePersist
    @PreUpdate
    public void calculateTotal() {
        if (products != null && !products.isEmpty()) {
            this.total = products.stream()
                    .mapToDouble(pis -> pis.getUnitPrice() * pis.getAmount())
                    .sum();
        }
    }
    
    

    
}
