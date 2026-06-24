package com.ashokIt.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequestDto {
	
	private Integer planId;

    private Integer statusId;

    private String gender;

    private LocalDate startDate;

    private LocalDate endDate;

}
