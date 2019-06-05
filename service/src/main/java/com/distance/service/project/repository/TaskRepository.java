package com.distance.service.project.repository;

import com.distance.service.common.base.BaseRepository;
import com.distance.service.project.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task, Integer> {
}
