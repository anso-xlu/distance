package com.distance.service.menu.model;

import com.distance.service.common.model.Tree;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


/**
 * 菜单
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@Data
public class Menu implements Tree<Menu, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String icon;

    private String name;

    private String url;
    /**
     * 排序等级
     */
    private Integer level;

    private Integer parentId;
    @Transient
    private List<Menu> children;

}
