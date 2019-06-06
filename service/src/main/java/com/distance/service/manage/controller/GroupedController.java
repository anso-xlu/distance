package com.distance.service.manage.controller;

import com.distance.service.common.base.BaseController;
import com.distance.service.common.model.Result;
import com.distance.service.common.support.Validator;
import com.distance.service.common.wrapper.Wrapper;
import com.distance.service.manage.model.Grouped;
import com.distance.service.manage.repository.GroupedRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/manage/group")
public class GroupedController extends BaseController<Grouped, Integer, GroupedRepository> {
    @Resource
    private GroupedRepository groupedRepository;
    @Resource
    private Validator validator;

    @GetMapping("getGroupLevel/{code}")
    public Result getGroupLevel(@PathVariable("code") Integer code) {
        int level = groupedRepository.getMaxLevel(code) + 1;
        return Wrapper.ok(level);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Grouped grouped) {
        if (grouped.getId() == null) {
            if (grouped.getCode() == null) {
                grouped.setCode(groupedRepository.getMaxCode() + 1);
            }
            if (grouped.getLevel() == null) {
                grouped.setLevel((Integer) getGroupLevel(grouped.getCode()).getData());
            }
        }

        validator.validate(grouped, Validator.save.class);

        grouped = groupedRepository.save(grouped);
        return Wrapper.ok(grouped);
    }


}
