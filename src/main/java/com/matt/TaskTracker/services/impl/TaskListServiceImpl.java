package com.matt.TaskTracker.services.impl;

import com.matt.TaskTracker.domain.entities.TaskList;
import com.matt.TaskTracker.repositories.TaskListRepository;
import com.matt.TaskTracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        if (taskList.getId() != null)
            throw new IllegalArgumentException("Task list already has an id!");
        if (taskList.getTitle() == null || taskList.getTitle().isEmpty())
            throw new IllegalArgumentException("Task list title can't be empty!");

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }
}
