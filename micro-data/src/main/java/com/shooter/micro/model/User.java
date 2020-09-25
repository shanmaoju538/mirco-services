package com.shooter.micro.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="micro_user")
public class User implements Serializable {

    private static final long serialVersionUID = 9101874114717470011L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name="address")
    private String address;

    @Column(name="isAvailable")
    private boolean isAvailable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createDate")
    private Date createDate;
}
