package com.distance.service.user.controller;

import com.distance.service.common.base.BaseController;
import com.distance.service.user.model.User;
import com.distance.service.user.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, Integer, UserRepository> {

    @Resource
    private UserRepository userRepository;
}
