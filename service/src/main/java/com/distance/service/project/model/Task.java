package com.distance.service.project.model;

import com.distance.service.common.model.History;
import com.distance.service.project.model.Project;
import com.distance.service.user.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Task implements History<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String version;
    private String name;
    private String description;
    private Project project;

    private Integer creator;
    private Date createTime;
    private Integer lastOperator;
    private Date updateTime;

    private Boolean end;

    private List<Plan> plans;
}
