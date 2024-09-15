package com.alyssonbarrera.todolist.task.services;

import com.alyssonbarrera.todolist.task.dtos.TaskListDTO;
import com.alyssonbarrera.todolist.task.entities.Task;
import com.alyssonbarrera.todolist.task.repositories.TasksRepository;
import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchTasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<TaskListDTO> execute(User user) {
        List<Task> tasks = this.tasksRepository.findAllByUser(user);

        return tasks.stream().map(TaskListDTO::new).toList();
    }
}
