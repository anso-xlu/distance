package com.distance.service.manage.model;

import com.distance.service.common.model.History;
import com.distance.service.project.model.Project;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Label implements History<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    private Integer creator;
    private Date createTime;
    private Integer lastOperator;
    private Date updateTime;
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @OneToMany
    private List<Project> projects;

    public enum Type {
        /**
         * 1.项目
         */
        PROJECT,
        /**
         * 2.项目成员
         */
        MEMBER,
        ;
    }
}
