package com.distance.service.common.model;

import java.io.Serializable;
import java.util.Date;

public interface History<ID extends Serializable> {
    /**
     * 创建人ID
     */
    ID getCreator();

    /**
     * 创建时间
     */
    Date getCreateTime();

    /**
     * 最后操作人ID
     */
    ID getLastOperator();

    /**
     * 更新时间
     */
    Date getUpdateTime();
}
