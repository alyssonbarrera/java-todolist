package com.alyssonbarrera.todolist.task.services;

import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.repositories.TasksRepository;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.utils.either.Either;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateTaskService {

    private final TasksRepository tasksRepository;

    public CreateTaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Either<AppError, TaskDTO> execute(Task task, User user) {
        task.setUser(user);

        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            return Either.left(new AppError(
                    "A data de início e a data de término devem ser maior que a data atual.",
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            return Either.left(new AppError(
                    "A data de início deve ser menor que a data de término.",
                    HttpStatus.BAD_REQUEST.value()
            ));
        }

        Task savedTask = this.tasksRepository.save(task);
        return Either.right(new TaskDTO(savedTask));
    }
}
