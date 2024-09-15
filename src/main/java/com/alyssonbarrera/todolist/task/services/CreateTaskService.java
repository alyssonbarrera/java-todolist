package com.alyssonbarrera.todolist.task.services;

import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.repositories.TasksRepository;
import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateTaskService {

    @Autowired
    private TasksRepository tasksRepository;

    public TaskDTO execute(Task task, User user) {
        task.setUser(user);
        Task savedTask =  this.tasksRepository.save(task);

        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            throw new AppError("A data de início e a data de término devem ser maior que a data atual.", HttpStatus.BAD_REQUEST.value());
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            throw new AppError("A data de início deve ser menor que a data de término.", HttpStatus.BAD_REQUEST.value());
        }

        return new TaskDTO(savedTask);
    }
}
