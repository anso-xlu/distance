package com.distance.service.project.controller;

import com.distance.service.common.model.Result;
import com.distance.service.common.support.Checker;
import com.distance.service.common.wrapper.Wrapper;
import com.distance.service.project.model.Project;
import com.distance.service.project.repository.ProjectRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectRepository repository;
    @Resource
    private Checker checker;

    @PostMapping
    public Result<Project> save(@RequestBody Project project) {
        checker.createCheck(project);

        checker.createCheck(project);

        return Wrapper.ok(project);
    }
}
