package com.distance.service.common.base;

import com.distance.service.common.model.Result;
import com.distance.service.common.wrapper.Wrapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public abstract class TableController<T, ID extends Serializable, R extends BaseRepository> extends BaseController<T, ID, R> {
    protected abstract R getRepository();

    @GetMapping("/id/{id}")
    @ResponseBody
    public Result<T> getById(@PathVariable("id") @NotNull ID id) {
        return Wrapper.ok(getRepository().findById(id).orElse(null));
    }

    /**
     * 分页搜索
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    public Result<T> searchPage(@Valid T vo, Integer pageNumber, Integer sizeNumber) {

        //不进行条件筛选
        Page<T> page;
        if (vo == null) {
            page = getRepository().findAll(PageRequest.of(pageNumber, sizeNumber));
        } else {
            //模糊匹配
            ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            page = getRepository().findAll(Example.of(vo, matcher), PageRequest.of(pageNumber, sizeNumber));
        }
        return Wrapper.ok(page);
    }

    /**
     * 保存
     *
     * @param vo
     * @return
     */
    @PostMapping
    @ResponseBody
    public Result<T> save(@Valid T vo) {
        return Wrapper.ok(getRepository().saveAndFlush(vo));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public Result<T> delete(@NotNull ID id) {
        getRepository().deleteById(id);
        return Wrapper.ok();
    }
}
