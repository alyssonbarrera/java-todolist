package com.alyssonbarrera.todolist.task.controllers;

import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.services.UpdateTaskService;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.utils.either.Either;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class UpdateTaskController {

    private final UpdateTaskService updateTaskService;

    public UpdateTaskController(UpdateTaskService updateTaskService) {
        this.updateTaskService = updateTaskService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> handle(@PathVariable UUID id, @RequestBody Task task, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        task.setId(id);
        Either<AppError, TaskDTO> result = this.updateTaskService.execute(task, user);

        if (result.isLeft()) {
            AppError error = result.getLeft();
            return ResponseEntity.status(error.getStatusCode()).body(error);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result.getRight());
    }
}
