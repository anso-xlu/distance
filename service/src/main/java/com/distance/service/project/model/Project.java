package com.distance.service.project.model;

import com.distance.service.common.model.History;
import com.distance.service.common.support.Checker;
import com.distance.service.manage.model.Label;
import com.distance.service.user.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@ToString(callSuper = true)
public class Project extends History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(groups = {Checker.Create.class})
    private String name;

    private String description;

    @ManyToMany
    private List<Label> labels;

    @Transient
    private List<User> member;

    @OneToMany(mappedBy = "project")
    private List<Version> versions;

}
