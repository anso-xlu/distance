package com.distance.service.project.controller;

import com.distance.service.common.base.BaseController;
import com.distance.service.project.model.Task;
import com.distance.service.project.repository.TaskRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project/task")
public class TaskController extends BaseController<Task, Integer, TaskRepository> {
    @Resource
    private TaskRepository taskRepository;
}
