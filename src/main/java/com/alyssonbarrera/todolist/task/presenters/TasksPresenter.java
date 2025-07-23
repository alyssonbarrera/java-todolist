package com.alyssonbarrera.todolist.task.presenters;

import com.alyssonbarrera.todolist.task.dtos.TaskListDTO;

import java.util.List;

public class TasksPresenter {
    private List<TaskListDTO> tasks;

    public TasksPresenter(List<TaskListDTO> tasks) {
        this.tasks = tasks;
    }

    public List<TaskListDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskListDTO> tasks) {
        this.tasks = tasks;
    }
}