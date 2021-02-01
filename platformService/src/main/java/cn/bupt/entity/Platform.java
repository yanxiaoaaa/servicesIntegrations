package cn.bupt.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Platform implements Serializable {
    Integer id;
    String name;
    Integer status;
    String url;
    String phone;
    String mail;
}
