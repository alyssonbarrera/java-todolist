package com.alyssonbarrera.todolist.task.repositories;

import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TasksRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByUser(User user);
}
