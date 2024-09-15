package com.alyssonbarrera.todolist.task.dtos;

import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.user.dtos.UserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UserDTO user;
    private LocalDateTime createdAt;

    public TaskDTO() {}

    public TaskDTO(Task entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.startAt = entity.getStartAt();
        this.endAt = entity.getEndAt();
        this.priority = entity.getPriority();
        this.user = new UserDTO(entity.getUser());
        this.createdAt = entity.getCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
