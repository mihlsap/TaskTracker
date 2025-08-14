package com.matt.TaskTracker.mappers.impl;

import com.matt.TaskTracker.domain.dto.TaskListDto;
import com.matt.TaskTracker.domain.entities.TaskList;
import com.matt.TaskTracker.domain.entities.TaskStatus;
import com.matt.TaskTracker.mappers.TaskListMapper;
import com.matt.TaskTracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList())
                        .orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                                .count() / (double) taskList.getTasks().size())
                        .orElse(null),
//                taskList.getTasks() != null ? taskList.getTasks().stream()
//                        .filter(task -> task.getStatus() == TaskStatus.CLOSED)
//                        .count() / (double) taskList.getTasks().size() : null,
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null)
        );
    }
}
