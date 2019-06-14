package com.distance.service.common.model;

import com.distance.service.common.constants.Const;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 接口响应的 Page
 * @param <T>
 */
@Getter
@AllArgsConstructor
public class PageWrapper<T> {
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

    public void setSizeNumber(Integer sizeNumber) {
        this.sizeNumber = sizeNumber != null ? sizeNumber : Const.SIZE_NUMBER;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber != null ? pageNumber - 1 : Const.PAGE_NUMBER;
    }
}
