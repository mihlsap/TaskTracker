package com.matt.TaskTracker.controllers;

import com.matt.TaskTracker.domain.dto.TaskListDto;
import com.matt.TaskTracker.domain.entities.TaskList;
import com.matt.TaskTracker.mappers.TaskListMapper;
import com.matt.TaskTracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        return taskListMapper
                .toDto(taskListService
                        .createTaskList(taskListMapper
                                .fromDto(taskListDto)));
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskListById(taskListId)
                .map(taskListMapper::toDto);
    }
}
