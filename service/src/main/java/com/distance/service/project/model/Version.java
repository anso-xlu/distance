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
public class Version extends History<Integer> {
    private Integer id;
    private String name;
    private String description;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "task")
    private List<Task> tasks;
}
