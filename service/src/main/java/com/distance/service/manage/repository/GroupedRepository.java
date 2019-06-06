package com.distance.service.manage.repository;

import com.distance.service.common.base.BaseRepository;
import com.distance.service.manage.model.Grouped;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupedRepository extends BaseRepository<Grouped, Integer> {
    @Query(value = "SELECT MAX(level) FROM grouped WHERE code=?1")
    int getMaxLevel(Integer code);

    @Query(value = "SELECT MAX(code) FROM grouped")
    int getMaxCode();
}
