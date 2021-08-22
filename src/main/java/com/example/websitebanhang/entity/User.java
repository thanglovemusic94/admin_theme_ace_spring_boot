package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(schema = "tbl", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor

public class User extends Base {
    public User() {
        super();
        this.enabled=false;
    }
    private String username;
    @Email
    private String email;

    private String password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
