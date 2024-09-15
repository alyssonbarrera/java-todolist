package com.alyssonbarrera.todolist.user.dtos;

import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private LocalDateTime createdAt;

    public UserDTO() {}

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
