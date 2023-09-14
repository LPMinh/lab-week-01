package com.example.labweek01.models;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id",columnDefinition = "varchar(50)")
    private String roleID;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;

    public Role(String roleID, String roleName, String description, String status) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.description = description;
        this.status = status;
    }

    public Role() {
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
