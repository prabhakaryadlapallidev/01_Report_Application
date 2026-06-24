package com.ashokIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokIt.Entity.PlaneNameMasterEntity;

@Repository
public interface PlaneNameMasterRepository extends JpaRepository<PlaneNameMasterEntity, Integer> {

}
