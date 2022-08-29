package com.mycalories.model2.user;

import com.mycalories.model2.UserRole.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();


}
