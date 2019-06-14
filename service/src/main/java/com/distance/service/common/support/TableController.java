package com.distance.service.common.support;

import com.distance.service.common.model.PageWrapper;
import com.distance.service.common.model.Result;
import com.distance.service.common.wrapper.Wrapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

public abstract class TableController<ID extends Serializable, T, R extends JpaRepository>{
    protected abstract R getRepository();
    
    @GetMapping("/id/{id}")
    @ResponseBody
    public Result<T> getById(@PathVariable("id") @NotNull ID id) {
        Optional<T> vo = getRepository().findById(id);
        return Wrapper.ok(vo.orElse(null));
    }

    /**
     * 分页搜索
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    public Result<PageWrapper<T>> searchPage(@Validated T vo, PageWrapper<T> pageWrapper) {
        //不进行条件筛选
        Page<T> page;
        if (vo == null) {
            page = getRepository().findAll(PageRequest.of(pageWrapper.getPageNumber(), pageWrapper.getSizeNumber()));
        } else {
            //模糊匹配
            ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            page = getRepository().findAll(Example.of(vo, matcher), PageRequest.of(pageWrapper.getPageNumber(), pageWrapper.getSizeNumber()));
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
    public Result<T> save(@Validated(Checker.Create.class) T vo) {
        return Wrapper.ok(getRepository().save(vo));
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
