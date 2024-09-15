package com.alyssonbarrera.todolist.task.controllers;

import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.services.UpdateTaskService;
import com.alyssonbarrera.todolist.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class UpdateTaskController {

    @Autowired
    private UpdateTaskService updateTaskService;

    @PutMapping("/{id}")
    public ResponseEntity handle(@PathVariable UUID id, @RequestBody Task task, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        task.setId(id);
        TaskDTO result = this.updateTaskService.execute(task, user);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
