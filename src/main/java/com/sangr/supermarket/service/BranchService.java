/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sangr.supermarket.service;

import com.sangr.supermarket.dto.BranchDTO;
import com.sangr.supermarket.entity.Branch;
import com.sangr.supermarket.exception.NotFoundException;
import com.sangr.supermarket.mapper.Mapper;
import com.sangr.supermarket.repository.BranchRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sangr
 */
@Service
@Transactional
public class BranchService implements IBranch{
    
    @Autowired
    private BranchRepository repo;

    @Override
    public List<BranchDTO> getBranches() {
        return repo.findAll().stream().map(Mapper::toBranchDTO).toList();
    }
    
    @Override
    public BranchDTO getBranch(Long id){
        Branch branch = repo.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));;
        BranchDTO branchDTO = Mapper.toBranchDTO(branch);
        return branchDTO;
    }
    
    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        Branch branch = Branch.builder().address(branchDTO.getAddress()).name(branchDTO.getName()).build();
        
        return Mapper.toBranchDTO(repo.save(branch));
    }

    @Override
    public BranchDTO updateBranch(BranchDTO branchDTO, Long id) {
        Branch branch = repo.findById(id)
        .orElseThrow(()-> new NotFoundException("Producto no encontrado"));
        
        branch.setAddress(branchDTO.getAddress());
        branch.setName(branchDTO.getName());
        
        return Mapper.toBranchDTO(repo.save(branch));
    }

    @Override
    public void deleteBranch(Long id) {
       Branch branch = repo.findById(id)
       .orElseThrow(()-> new NotFoundException("Producto no encontrado"));;
       
       repo.deleteById(id);
    }
    
}
