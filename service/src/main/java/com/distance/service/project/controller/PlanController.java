package com.distance.service.project.controller;

import com.distance.service.project.repository.PlanRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project/plan")
public class PlanController {
    @Resource
    private PlanRepository repository;
}
