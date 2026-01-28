/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.repository;

import com.sangr.supermarket.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sangr
 */
public interface BranchRepository extends JpaRepository<Branch,Long>{
    
}
