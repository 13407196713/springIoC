package com.southwind.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private long id;
    private String name;
    private int age;
    private List<Address> addressess;

    public User(){
        System.out.println("User对象创建");
    }
}
