package com.taskmanager.service;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.dto.TaskStatsResponse;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import com.taskmanager.entity.User;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.exception.UnauthorizedException;
import com.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final AuthService authService;

    public TaskService(TaskRepository taskRepository, AuthService authService) {
        this.taskRepository = taskRepository;
        this.authService = authService;
    }

    @Transactional
    public TaskResponse createTask(TaskRequest request, String userEmail) {
        User user = authService.getAuthenticatedUser(userEmail);

        Task task = Task.builder()
                .title(request.getTitle().trim())
                .description(request.getDescription() != null ? request.getDescription().trim() : null)
                .status(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO)
                .priority(request.getPriority() != null ? request.getPriority() : TaskPriority.MEDIUM)
                .dueDate(request.getDueDate())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);
        return mapToTaskResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> getTasks(
            TaskStatus status,
            TaskPriority priority,
            String search,
            Pageable pageable,
            String userEmail
    ) {
        User user = authService.getAuthenticatedUser(userEmail);
        String trimmedSearch = (search != null && !search.trim().isEmpty()) ? search.trim() : null;

        Page<Task> taskPage = taskRepository.findByUserWithFilters(user, status, priority, trimmedSearch, pageable);
        return taskPage.map(this::mapToTaskResponse);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id, String userEmail) {
        User user = authService.getAuthenticatedUser(userEmail);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to view this task");
        }

        return mapToTaskResponse(task);
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request, String userEmail) {
        User user = authService.getAuthenticatedUser(userEmail);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to modify this task");
        }

        task.setTitle(request.getTitle().trim());
        task.setDescription(request.getDescription() != null ? request.getDescription().trim() : null);
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }
        task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return mapToTaskResponse(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id, String userEmail) {
        User user = authService.getAuthenticatedUser(userEmail);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this task");
        }

        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    public TaskStatsResponse getTaskStats(String userEmail) {
        User user = authService.getAuthenticatedUser(userEmail);

        long total = taskRepository.countByUser(user);
        long completed = taskRepository.countByUserAndStatus(user, TaskStatus.COMPLETED);
        long inProgress = taskRepository.countByUserAndStatus(user, TaskStatus.IN_PROGRESS);
        long pending = taskRepository.countByUserAndStatus(user, TaskStatus.TODO);

        double rate = total > 0 ? ((double) completed / total) * 100.0 : 0.0;
        double roundedRate = Math.round(rate * 10.0) / 10.0;

        return TaskStatsResponse.builder()
                .totalTasks(total)
                .completedTasks(completed)
                .inProgressTasks(inProgress)
                .pendingTasks(pending)
                .completionRate(roundedRate)
                .build();
    }

    public TaskResponse mapToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .userId(task.getUser().getId())
                .build();
    }
}
