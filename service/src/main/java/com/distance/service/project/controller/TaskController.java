package com.distance.service.project.controller;

import com.distance.service.project.repository.TaskRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project/task")
public class TaskController {
    @Resource
    private TaskRepository repository;


}
