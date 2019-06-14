package com.distance.service.project.model;

import com.distance.service.common.model.History;
import com.distance.service.common.support.Checker;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@Data
public class Task extends History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(groups = {Checker.Create.class})
    private String name;
    private String description;

    private boolean over;

    @NotNull(groups = {Checker.Create.class})
    @ManyToOne
    private Version version;

    @OneToMany(mappedBy = "task")
    private List<Plan> plans;
}
