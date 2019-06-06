package com.distance.service.project.model;

import com.distance.service.common.model.History;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@Data
public class Task extends History<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String version;
    private String name;
    private String description;
    private Project project;

    private Boolean end;

    @OneToMany(mappedBy = "task")
    private List<Plan> plans;
}
