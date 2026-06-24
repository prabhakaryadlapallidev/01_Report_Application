package com.ashokIt.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ashokIt.Entity.CitizenPlanEntity;
import com.ashokIt.dto.SearchRequestDto;

import jakarta.persistence.criteria.Predicate;

public class CitizenPlanSpecification {

	
	public static Specification<CitizenPlanEntity>  getSearchSpec(SearchRequestDto request)
	{
		 return (root, query, cb) -> {

            Predicate predicate =
                    cb.conjunction();

            //plan filter
            if(request.getPlanId()!=null)
            {
            	predicate=cb.and(predicate,
            			cb.equal(root.get("plane").get("planeNameId"),request.getPlanId()
            					)
            			);
            }
            
            //status filter
            if(request.getStatusId()!=null)
            {
            	predicate=cb.and(predicate,cb.equal(root.get("status").get("planeStatusId"),request.getStatusId()));
            }
            
            //gender filter
            if (request.getGender() != null
                    && !request.getGender().isBlank()) {

                predicate = cb.and(
                        predicate,
                        cb.equal(
                                root.get("citizenGender"),
                                request.getGender()
                        )
                );
            }
            
            return predicate;
        };
	}
}
