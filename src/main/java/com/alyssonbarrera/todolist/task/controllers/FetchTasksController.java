package com.alyssonbarrera.todolist.task.controllers;

import com.alyssonbarrera.todolist.task.dtos.TaskListDTO;
import com.alyssonbarrera.todolist.task.presenters.TasksPresenter;
import jakarta.servlet.http.HttpServletRequest;
import com.alyssonbarrera.todolist.task.services.FetchTasksService;
import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class FetchTasksController {

    private final FetchTasksService fetchTasksService;

    public FetchTasksController(FetchTasksService fetchTasksService) {
        this.fetchTasksService = fetchTasksService;
    }

    @GetMapping("")
    public ResponseEntity<TasksPresenter> handle(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<TaskListDTO> tasks = this.fetchTasksService.execute(user);

        TasksPresenter response = new TasksPresenter(tasks);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}