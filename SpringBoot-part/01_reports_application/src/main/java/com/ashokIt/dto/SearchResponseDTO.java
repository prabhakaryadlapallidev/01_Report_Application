package com.ashokIt.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponseDTO {
	 private Integer citizenId;

	    private String citizenName;

	    private String citizenGender;

	    private String planName;

	    private String planStatus;

	    private LocalDate planStartDate;

	    private LocalDate planEndDate;

	    private Double benefitAmount;
}
