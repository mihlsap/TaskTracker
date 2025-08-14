package com.matt.TaskTracker.mappers;

import com.matt.TaskTracker.domain.dto.TaskDto;
import com.matt.TaskTracker.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
