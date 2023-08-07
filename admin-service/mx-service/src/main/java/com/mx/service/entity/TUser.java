package com.mx.service.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "t_user")
public class TUser implements Serializable {
    @Id
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "ip")
    private String ip;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "update_date")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}