package com.distance.service.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class History {
    /**
     * 创建人ID
     */
    protected Integer creator;
    /**
     * 创建时间
     */
    @CreationTimestamp
    protected Date createTime;
    /**
     * 最后操作人ID
     */
    protected Integer lastOperator;
    /**
     * 更新时间
     */
    @UpdateTimestamp
    protected Date updateTime;
}
