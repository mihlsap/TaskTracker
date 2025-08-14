package com.matt.TaskTracker.domain.dto;

import com.matt.TaskTracker.domain.entities.TaskPriority;
import com.matt.TaskTracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
