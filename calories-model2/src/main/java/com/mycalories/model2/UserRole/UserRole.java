package com.mycalories.model2.UserRole;

import com.mycalories.model2.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role")
    private String role;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
