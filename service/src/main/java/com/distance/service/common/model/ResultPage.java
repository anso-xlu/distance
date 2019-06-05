package com.distance.service.common.model;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 接口响应的 Page
 * @param <T>
 */
@Getter
public class ResultPage<T> {
    /**
     * 返回数据
     */
    private List<T> data;
    /**
     * 单页大小
     */
    private Integer sizeNumber;
    /**
     * 页数
     */
    private Integer pageNumber;
    /**
     * 下一页
     */
    private Boolean hasNext;
    /**
     * 是否首页
     */
    private Boolean isFirst;
    /**
     * 是否尾页
     */
    private Boolean isLast;
    /**
     * 总页面大小
     */
    private int pageTotal;
    /**
     * 总数据大小
     */
    private long sizeTotal;

    public ResultPage(Page page) {
        this.data = page.getContent();
        this.sizeNumber = page.getSize();
        this.pageNumber = page.getNumber();
        this.hasNext = page.hasNext();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.pageTotal = page.getTotalPages();
        this.sizeTotal = page.getTotalElements();
    }
}
