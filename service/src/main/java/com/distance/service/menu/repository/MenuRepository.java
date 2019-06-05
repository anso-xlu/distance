package com.distance.service.menu.repository;

import com.distance.service.common.base.BaseRepository;
import com.distance.service.menu.model.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends BaseRepository<Menu, Integer> {
}
