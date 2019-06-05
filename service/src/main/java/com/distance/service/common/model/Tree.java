package com.distance.service.common.model;


import java.io.Serializable;
import java.util.List;

/**
 * tree 对象继承, treeWrapper
 */
public interface Tree<T extends Tree, ID extends Serializable> {

    ID getId();

    ID getParentId();

    void setChildren(List<T> list);

    /**
     * 排序等级
     */
    Integer getLevel();

}
