package com.mycompany.user;

import java.time.LocalDateTime;

public class User extends Entity {
    private String name;
    private String email;
    private String password;
    private LocalDateTime lastAccess;
    private Boolean active;

    public User(String name, String email, String password, LocalDateTime lastAccess, boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastAccess = lastAccess;
        this.active = active;
    }

    User() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }
}
