package com.distance.service.project.repository;

import com.distance.service.common.base.BaseRepository;
import com.distance.service.project.model.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Integer> {
}
