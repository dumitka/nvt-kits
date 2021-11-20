package com.backend.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role { //implements GrantedAuthority

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

//    @JsonIgnore
//    @Override
//    public String getAuthority() {
//        return name;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
