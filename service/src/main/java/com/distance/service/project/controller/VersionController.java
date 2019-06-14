package com.distance.service.project.controller;

import com.distance.service.common.constants.ECode;
import com.distance.service.common.model.Result;
import com.distance.service.common.support.Checker;
import com.distance.service.common.wrapper.Wrapper;
import com.distance.service.project.model.Version;
import com.distance.service.project.repository.ProjectRepository;
import com.distance.service.project.repository.VersionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project/version")
public class VersionController {
    @Resource
    private VersionRepository repository;
    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private Checker checker;


    @PostMapping
    public Result<Version> save(@RequestBody Version version) {
        if (version.getProject() == null || !projectRepository.findById(version.getProject().getId()).isPresent()) {
            return Wrapper.error(ECode.G_0001, "所属项目不能为空");
        }
        if (version.getId() == null) {
            checker.createCheck(version);
        }

        repository.saveAndFlush(version);
        return Wrapper.ok(version);
    }
}
