package com.casestudy.pension.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.pension.entities.PensionerDetail;

@Repository
public interface PensionerDetailRepository extends JpaRepository<PensionerDetail,Integer> {

	Optional<PensionerDetail> findByAadhaarNumber(String aadhaarNumber);
}
