package com.distance.service.manage.controller;

import com.distance.service.common.model.Result;
import com.distance.service.common.support.Checker;
import com.distance.service.common.wrapper.Wrapper;
import com.distance.service.manage.model.Grouped;
import com.distance.service.manage.repository.GroupedRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/manage/group")
public class GroupedController {
    @Resource
    private GroupedRepository repository;
    @Resource
    private Checker checker;

    @GetMapping("getNextSort")
    public Result<Integer> getNextSort() {
        int level = repository.findMaxSort() + 1;
        return Wrapper.ok(level);
    }

    @PostMapping("/save")
    public Result<Grouped> save(@RequestBody Grouped group) {
        if (group.getId() == null) {
            if (group.getSort() == null) {
                group.setSort(getNextSort().getData());
            }
        }

        checker.createCheck(group);

        group = repository.save(group);
        return Wrapper.ok(group);
    }


}
