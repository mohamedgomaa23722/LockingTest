package com.mg.js_user_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ReqTable")
public class ReqTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String status;

    public ReqTable() {
    }

    @Override
    public String toString() {
        return "(id=" + id +"),";
    }

    public ReqTable(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
