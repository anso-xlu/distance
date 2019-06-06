package com.distance.service.project.model;

import com.distance.service.manage.model.Grouped;
import com.distance.service.manage.model.Label;
import com.distance.service.user.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Date startTime;

    private Date planTime;

    private Date endTime;

    private User member;
    private Grouped grouped;

    @ManyToOne
    private Task task;
}
