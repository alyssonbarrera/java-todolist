package com.alyssonbarrera.todolist.task.controllers;

import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.services.CreateTaskService;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.utils.either.Either;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class CreateTaskController {

    private final CreateTaskService createTaskService;

    public CreateTaskController(CreateTaskService createTaskService) {
        this.createTaskService = createTaskService;
    }

    @PostMapping("")
    public ResponseEntity<?> handle(@RequestBody Task task, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        Either<AppError, TaskDTO> result = createTaskService.execute(task, user);

        if (result.isLeft()) {
            AppError error = result.getLeft();
            return ResponseEntity.status(error.getStatusCode()).body(error);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result.getRight());
    }
}
