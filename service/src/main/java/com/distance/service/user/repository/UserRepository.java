package com.distance.service.user.repository;

import com.distance.service.common.base.BaseRepository;
import com.distance.service.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {
}
