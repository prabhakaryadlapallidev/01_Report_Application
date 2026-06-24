package com.ashokIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokIt.Entity.PlaneStatusMasterEntity;

@Repository
public interface PlaneStatusMasterRepository extends JpaRepository<PlaneStatusMasterEntity, Integer> {

}
