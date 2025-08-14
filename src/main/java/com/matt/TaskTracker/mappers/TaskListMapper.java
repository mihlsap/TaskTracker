package com.matt.TaskTracker.mappers;

import com.matt.TaskTracker.domain.dto.TaskListDto;
import com.matt.TaskTracker.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto TaskListDto);

    TaskListDto toDto(TaskList taskList);
}
