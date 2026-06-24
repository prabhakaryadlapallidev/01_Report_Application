package com.ashokIt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ashokIt.Entity.CitizenPlanEntity;
import com.ashokIt.dto.SearchRequestDto;
import com.ashokIt.dto.SearchResponseDTO;
import com.ashokIt.repo.CitizenPlanRepository;
import com.ashokIt.specification.CitizenPlanSpecification;

@Service
public class SearchResponseService {

	@Autowired
	private CitizenPlanRepository citizenPlanRepository;
	
	public List<SearchResponseDTO> search(SearchRequestDto request) {
		Specification<CitizenPlanEntity> spec =
		           CitizenPlanSpecification
		                .getSearchSpec(request);

		    List<CitizenPlanEntity> entities =
		           citizenPlanRepository.findAll(spec);

		    List<SearchResponseDTO> responseDtos = new ArrayList<>();

		    for (CitizenPlanEntity entity : entities) {

		        SearchResponseDTO dto = new SearchResponseDTO();

		        dto.setCitizenId(entity.getCitizenId());
		        dto.setCitizenName(entity.getCitizenName());
		        dto.setCitizenGender(entity.getCitizenGender());

		        dto.setPlanName(entity.getPlane().getPlaneName());

		        dto.setPlanStatus(entity.getStatus().getPlaneStatus());

		        dto.setPlanStartDate(
		                entity.getPlanStartDate().toLocalDate());

		        dto.setPlanEndDate(
		                entity.getPlanEndDate().toLocalDate());

		        dto.setBenefitAmount(entity.getBenefitAmount());

		        responseDtos.add(dto);
		    }

	        return responseDtos;
	}

}
