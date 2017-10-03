package com.udemy.backendninja2.entity;

import javax.persistence.*;

//Encargada de almacenar los Roles de Usuarios
@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(
        columnNames = {"role", "username"}
))

public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "user_role_id", unique = true, nullable = false) //Es único y no puede ser null
    private Integer userRoleId;     //Su id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false) //Hace un join con username
    private User user;  //Usuario al que hará referencia

    @Column(name = "role", nullable = false, length = 45)
    private String role; // Y el tipo de rol

    public UserRole() {
    }

    public UserRole(Integer userRoleId, User user, String role) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
