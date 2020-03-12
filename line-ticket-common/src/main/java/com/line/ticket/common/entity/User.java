package com.line.ticket.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 4607877128899355532L;
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String phoneNumber;
    private String email;
    private Integer sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
