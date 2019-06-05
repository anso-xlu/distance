package com.distance.service.project.repository;

import com.distance.service.common.base.BaseRepository;
import com.distance.service.project.model.Plan;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends BaseRepository<Plan, Integer> {
}
