package com.distance.service.project.controller;

import com.distance.service.common.base.BaseController;
import com.distance.service.project.model.Project;
import com.distance.service.project.repository.ProjectRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController<Project, Integer, ProjectRepository> {
}
