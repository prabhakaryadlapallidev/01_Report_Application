package com.ashokIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ashokIt.Entity.CitizenPlanEntity;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlanEntity, Integer>, JpaSpecificationExecutor<CitizenPlanEntity> {

}
