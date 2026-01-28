/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sangr.supermarket.service;

import com.sangr.supermarket.dto.BranchDTO;
import java.util.List;

/**
 *
 * @author sangr
 */
public interface IBranch {
    List<BranchDTO> getBranches (); //Get
    BranchDTO getBranch(Long id);
    BranchDTO createBranch (BranchDTO branchDTO); //Create
    BranchDTO updateBranch (BranchDTO branchDTO, Long id); //Update
    void deleteBranch(Long id); //Delete
}
