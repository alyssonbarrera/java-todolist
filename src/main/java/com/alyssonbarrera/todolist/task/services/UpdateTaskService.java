package com.alyssonbarrera.todolist.task.services;

import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.task.dtos.TaskDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.repositories.TasksRepository;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskService {

    @Autowired
    private TasksRepository tasksRepository;

    public TaskDTO execute(Task task, User user) {
        Task taskOnDatabase = this.tasksRepository.findById(task.getId()).orElse(null);

        if (taskOnDatabase == null) {
            throw new AppError("Tarefa não encontrada.", HttpStatus.NOT_FOUND.value());
        }

        if (!taskOnDatabase.getUser().getId().equals(user.getId())) {
            throw new AppError("Você não tem permissão para atualizar a tarefa de outro usuário.", HttpStatus.FORBIDDEN.value());
        }

        task.setUser(user);
        Utils.copyNonNullProperties(task, taskOnDatabase);

        Task updatedTask = this.tasksRepository.save(taskOnDatabase);

        return new TaskDTO(updatedTask);
    }
}
