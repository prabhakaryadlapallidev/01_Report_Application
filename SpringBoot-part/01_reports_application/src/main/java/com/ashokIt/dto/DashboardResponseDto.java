package com.ashokIt.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashboardResponseDto {

	private List<PlaneDto> plans;
	private List<StatusDto>statuses;
}
