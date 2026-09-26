package com.taskmanager.dto;

import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;

    private String description;

    private TaskStatus status = TaskStatus.TODO;

    private TaskPriority priority = TaskPriority.MEDIUM;

    private LocalDate dueDate;

    public TaskRequest() {}

    public TaskRequest(String title, String description, TaskStatus status, TaskPriority priority, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.status = status != null ? status : TaskStatus.TODO;
        this.priority = priority != null ? priority : TaskPriority.MEDIUM;
        this.dueDate = dueDate;
    }

    public static TaskRequestBuilder builder() {
        return new TaskRequestBuilder();
    }

    public static class TaskRequestBuilder {
        private String title;
        private String description;
        private TaskStatus status = TaskStatus.TODO;
        private TaskPriority priority = TaskPriority.MEDIUM;
        private LocalDate dueDate;

        public TaskRequestBuilder title(String title) { this.title = title; return this; }
        public TaskRequestBuilder description(String description) { this.description = description; return this; }
        public TaskRequestBuilder status(TaskStatus status) { this.status = status; return this; }
        public TaskRequestBuilder priority(TaskPriority priority) { this.priority = priority; return this; }
        public TaskRequestBuilder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }

        public TaskRequest build() {
            return new TaskRequest(title, description, status, priority, dueDate);
        }
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
