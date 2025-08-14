package com.matt.TaskTracker.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
