package com.distance.service.menu.controller;

import com.distance.service.common.base.BaseController;
import com.distance.service.common.model.Result;
import com.distance.service.common.wrapper.TreeWrapper;
import com.distance.service.common.wrapper.Wrapper;
import com.distance.service.menu.model.Menu;
import com.distance.service.menu.repository.MenuRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu, Integer, MenuRepository> {
    @Resource
    private MenuRepository menuRepository;

    @GetMapping("/getMenu")
    public Result getMenu() {
        List<Menu> menus = TreeWrapper.asTree(menuRepository.findAll(), 0);
        return Wrapper.ok(menus);
    }
}
