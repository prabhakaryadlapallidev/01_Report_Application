package com.ashokIt.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ashokIt.Entity.CitizenPlanEntity;
import com.ashokIt.Entity.PlaneNameMasterEntity;
import com.ashokIt.Entity.PlaneStatusMasterEntity;
import com.ashokIt.dto.DashboardResponseDto;
import com.ashokIt.dto.PlaneDto;
import com.ashokIt.dto.SearchRequestDto;
import com.ashokIt.dto.StatusDto;
import com.ashokIt.repo.CitizenPlanRepository;
import com.ashokIt.repo.PlaneNameMasterRepository;
import com.ashokIt.repo.PlaneStatusMasterRepository;
import com.ashokIt.specification.CitizenPlanSpecification;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private PlaneNameMasterRepository planeRepo;

	@Autowired
	private PlaneStatusMasterRepository statusRepo;
	
	@Autowired
	private CitizenPlanRepository citizenPlanRepo;
	
	@Override
	public DashboardResponseDto getDropdownData() {
		
		DashboardResponseDto response = new DashboardResponseDto();
		
		List<PlaneNameMasterEntity> planeEntities=planeRepo.findAll();
		
		List<PlaneDto> planeDtos = new ArrayList<>();
		
		
		for(PlaneNameMasterEntity entity:planeEntities)
		{
			PlaneDto dto=new PlaneDto();
			dto.setPlanId(entity.getPlaneNameId());
			dto.setPlanName(entity.getPlaneName());
			planeDtos.add(dto);
		}
		
		List<PlaneStatusMasterEntity> statusEntities=statusRepo.findAll();
		List<StatusDto> statusDtos = new ArrayList<>();
		
		for(PlaneStatusMasterEntity entity:statusEntities)
		{
			StatusDto dto=new StatusDto();
			dto.setStatusId(entity.getPlaneStatusId());
			dto.setStatusName(entity.getPlaneStatus());
			statusDtos.add(dto);
		}
		response.setPlans(planeDtos);
		response.setStatuses(statusDtos);
		
		return response;
	}

	@Override
	public void exportExcel(SearchRequestDto request, HttpServletResponse response) {

	    try {

	        Specification<CitizenPlanEntity> spec =
	                CitizenPlanSpecification.getSearchSpec(request);

	        List<CitizenPlanEntity> records = citizenPlanRepo.findAll(spec);

	        XSSFWorkbook workbook = new XSSFWorkbook();

	        XSSFSheet sheet = workbook.createSheet("Citizen Report");

	        // Header Row
	        Row header = sheet.createRow(0);

	        header.createCell(0).setCellValue("Citizen Id");
	        header.createCell(1).setCellValue("Citizen Name");
	        header.createCell(2).setCellValue("Gender");
	        header.createCell(3).setCellValue("Plan Name");
	        header.createCell(4).setCellValue("Plan Status");
	        header.createCell(5).setCellValue("Benefit Amount");

	        int rowNum = 1;

	        for (CitizenPlanEntity record : records) {

	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0).setCellValue(record.getCitizenId());

	            row.createCell(1).setCellValue(record.getCitizenName());

	            row.createCell(2).setCellValue(record.getCitizenGender());

	            row.createCell(3)
	                    .setCellValue(record.getPlane().getPlaneName());

	            row.createCell(4)
	                    .setCellValue(record.getStatus().getPlaneStatus());

	            row.createCell(5)
	                    .setCellValue(record.getBenefitAmount());
	        }

	        workbook.write(response.getOutputStream());

	        workbook.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	}

	@Override
	public void exportPdf(SearchRequestDto request,
	                      HttpServletResponse response) {

	    try {

	        Specification<CitizenPlanEntity> spec =
	                CitizenPlanSpecification.getSearchSpec(request);

	        List<CitizenPlanEntity> records = citizenPlanRepo.findAll(spec);

	        Document document = new Document();

	        PdfWriter.getInstance(document,
	                response.getOutputStream());

	        document.open();

	        document.add(new Paragraph("Citizen Report"));

	        document.add(new Paragraph(" "));

	        PdfPTable table = new PdfPTable(6);

	        table.addCell("Citizen Id");
	        table.addCell("Citizen Name");
	        table.addCell("Gender");
	        table.addCell("Plan Name");
	        table.addCell("Plan Status");
	        table.addCell("Benefit Amount");

	        for (CitizenPlanEntity record : records) {

	            table.addCell(String.valueOf(record.getCitizenId()));

	            table.addCell(record.getCitizenName());

	            table.addCell(record.getCitizenGender());

	            table.addCell(record.getPlane().getPlaneName());

	            table.addCell(record.getStatus().getPlaneStatus());

	            table.addCell(String.valueOf(record.getBenefitAmount()));
	        }

	        document.add(table);

	        document.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	}

}
