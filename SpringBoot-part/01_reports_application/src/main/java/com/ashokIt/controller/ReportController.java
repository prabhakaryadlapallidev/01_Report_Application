package com.ashokIt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokIt.dto.DashboardResponseDto;
import com.ashokIt.dto.SearchRequestDto;
import com.ashokIt.dto.SearchResponseDTO;
import com.ashokIt.service.ReportService;
import com.ashokIt.service.SearchResponseService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private SearchResponseService searchResponseService;
	
	@GetMapping("/init")
	public DashboardResponseDto init() {
	 return reportService.getDropdownData();
	}
	
	@PostMapping("/search")
	public List<SearchResponseDTO> search(
	        @RequestBody SearchRequestDto request) {

	    return searchResponseService.search(request);
	}
	
	 @PostMapping("/excel")
	    public void exportExcel(
	            @RequestBody SearchRequestDto request,
	            HttpServletResponse response) throws Exception {

	        response.setContentType(
	                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	        response.setHeader(
	                "Content-Disposition",
	                "attachment; filename=Citizen_Report.xlsx");

	        reportService.exportExcel(request, response);
	    }
	
	 @PostMapping("/pdf")
	    public void exportPdf(
	            @RequestBody SearchRequestDto request,
	            HttpServletResponse response) throws Exception {

	        response.setContentType("application/pdf");

	        response.setHeader(
	                "Content-Disposition",
	                "attachment; filename=Citizen_Report.pdf");

	        reportService.exportPdf(request, response);
	    }
System.out.println("fetch command");
}
