package com.distance.service.common.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class History<ID extends Serializable> {
    /**
     * 创建人ID
     */
    protected ID creator;
    /**
     * 创建时间
     */
    @CreationTimestamp
    protected Date createTime;
    /**
     * 最后操作人ID
     */
    protected ID lastOperator;
    /**
     * 更新时间
     */
    @UpdateTimestamp
    protected Date updateTime;
}
