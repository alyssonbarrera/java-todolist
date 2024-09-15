package com.alyssonbarrera.todolist.task.controllers;

import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.services.CreateTaskService;
import com.alyssonbarrera.todolist.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class CreateTaskController {

    @Autowired
    private CreateTaskService createTaskService;

    @PostMapping("")
    public ResponseEntity handle(@RequestBody Task task, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        TaskDTO result = this.createTaskService.execute(task, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
