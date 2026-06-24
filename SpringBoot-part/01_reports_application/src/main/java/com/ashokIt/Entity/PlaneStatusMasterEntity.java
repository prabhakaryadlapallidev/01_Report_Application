package com.ashokIt.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "plane_status_master")
public class PlaneStatusMasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int planeStatusId;
	private String planeStatus;
}

