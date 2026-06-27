package com.ashokIt.service;

import com.ashokIt.dto.DashboardResponseDto;
import com.ashokIt.dto.SearchRequestDto;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	 DashboardResponseDto getDropdownData();

	void exportExcel(SearchRequestDto request, HttpServletResponse response);
	void exportPdf(
			SearchRequestDto request,
            HttpServletResponse response);

}
