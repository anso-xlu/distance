package com.distance.service.manage.model;

import com.distance.service.common.model.History;
import com.distance.service.common.support.Checker;
import com.distance.service.project.model.Project;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@Data
public class Label extends History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(groups = {Checker.Create.class})
    private String name;

    private String description;

    private Integer sort;

    @ManyToMany(mappedBy = "labels")
    private List<Project> projects;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    public enum Type {
        DEFAULT_GROUP,
        GROUP,
        PROJECT
    }
}
