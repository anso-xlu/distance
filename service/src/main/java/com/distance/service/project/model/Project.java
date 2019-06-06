package com.distance.service.project.model;

import com.distance.service.common.model.History;
import com.distance.service.manage.model.Grouped;
import com.distance.service.manage.model.Label;
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
public class Project extends History<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "project_label", inverseJoinColumns = @JoinColumn(name = "label_id"), joinColumns = @JoinColumn(name = "project_id"))
    private List<Label> labels;

    @OneToMany
    private List<Grouped> grouped;

    @OneToMany(mappedBy = "project")
    private List<Version> versions;
}
