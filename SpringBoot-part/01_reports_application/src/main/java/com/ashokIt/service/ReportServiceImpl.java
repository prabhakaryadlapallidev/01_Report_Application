package com.ashokIt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokIt.Entity.PlaneNameMasterEntity;
import com.ashokIt.Entity.PlaneStatusMasterEntity;
import com.ashokIt.dto.DashboardResponseDto;
import com.ashokIt.dto.PlaneDto;
import com.ashokIt.dto.StatusDto;
import com.ashokIt.repo.PlaneNameMasterRepository;
import com.ashokIt.repo.PlaneStatusMasterRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private PlaneNameMasterRepository planeRepo;

	@Autowired
	private PlaneStatusMasterRepository statusRepo;
	
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

}
