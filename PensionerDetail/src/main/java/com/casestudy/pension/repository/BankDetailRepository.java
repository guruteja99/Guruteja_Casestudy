package com.casestudy.pension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.pension.entities.BankDetail;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetail,Integer>{

}
