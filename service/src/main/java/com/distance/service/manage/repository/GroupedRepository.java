package com.distance.service.manage.repository;

import com.distance.service.manage.model.Grouped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupedRepository extends JpaRepository<Grouped, Integer> {

    @Query(value = "SELECT MAX(level) FROM grouped")
    int findMaxSort();

}
