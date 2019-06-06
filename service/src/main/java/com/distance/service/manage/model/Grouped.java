package com.distance.service.manage.model;

import com.distance.service.common.model.History;
import com.distance.service.common.support.Validator;
import com.distance.service.user.model.User;
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
public class Grouped extends History<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(groups = {Validator.save.class})
    private Integer code;

    @NotBlank(groups = {Validator.save.class})
    private String name;

    private String description;

    @NotNull(groups = {Validator.save.class})
    private Integer level;

    @OneToMany(mappedBy = "grouped")
    private List<User> members;
}
