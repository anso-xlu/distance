package com.distance.service.project.model;

import com.distance.service.manage.model.Label;
import com.distance.service.user.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date startTime;
    private Date planTime;
    private Date endTime;
    private User member;
    private Label label;
}
