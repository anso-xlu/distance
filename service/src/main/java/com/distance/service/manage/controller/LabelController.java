package com.distance.service.manage.controller;

import com.distance.service.common.model.Result;
import com.distance.service.manage.model.Label;
import com.distance.service.manage.repository.LabelRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/manage/label")
public class LabelController {
    @Resource
    private LabelRepository repository;

}
