package com.ashokIt.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "citizen_plan")
public class CitizenPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int citizenId;
	private String citizenName;
	private String citizenGender;
	private LocalDateTime planStartDate;
	private LocalDateTime planEndDate;
	private Double benefitAmount;
	@ManyToOne
	@JoinColumn(name = "plane_name_id")
	private PlaneNameMasterEntity plane;
	
	@ManyToOne
	@JoinColumn(name = "plane_status_id")
	private PlaneStatusMasterEntity status;
	
}
