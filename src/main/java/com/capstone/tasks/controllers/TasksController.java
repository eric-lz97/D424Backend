package com.capstone.tasks.controllers;

import com.capstone.tasks.domain.dto.TaskDto;
import com.capstone.tasks.domain.entities.Task;
import com.capstone.tasks.mappers.TaskMapper;
import com.capstone.tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "https://tasks-ui.onrender.com")
@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto){
        Task createdTask = taskService.createTask(
                taskListId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }

    @CrossOrigin(origins = "https://tasks-ui.onrender.com")
    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id" ) UUID taskId
            ) {
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @CrossOrigin(origins = "https://tasks-ui.onrender.com")
    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto
    ) {
        Task updatedTask = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto)
        );

        return taskMapper.toDto(updatedTask);
    }

    @CrossOrigin(origins = "https://tasks-ui.onrender.com")
    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ) {
        taskService.deleteTask(taskListId, taskId);
    }
    @CrossOrigin(origins = "https://tasks-ui.onrender.com")
    @GetMapping("/search")
    public List<TaskDto> searchTasks(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestParam("query") String query
    ) {
        return taskService.searchTasks(taskListId, query).stream()
                .map(taskMapper::toDto)
                .toList();
    }

}
