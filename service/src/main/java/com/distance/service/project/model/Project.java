package com.distance.service.project.model;

import com.distance.service.common.model.History;
import com.distance.service.manage.model.Label;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Project implements History<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    private Integer creator;
    private Date createTime;
    private Integer lastOperator;
    private Date updateTime;

    @OneToMany
    private List<Label> labels;
    @OneToMany
    private List<Task> tasks;
}
