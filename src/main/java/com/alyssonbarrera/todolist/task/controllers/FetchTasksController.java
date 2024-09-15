package com.alyssonbarrera.todolist.task.controllers;

import com.alyssonbarrera.todolist.task.dtos.TaskListDTO;
import jakarta.servlet.http.HttpServletRequest;
import com.alyssonbarrera.todolist.task.services.FetchTasksService;
import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class FetchTasksController {

    @Autowired
    private FetchTasksService fetchTasksService;

    @GetMapping("")
    public ResponseEntity handle(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<TaskListDTO> result = this.fetchTasksService.execute(user);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
