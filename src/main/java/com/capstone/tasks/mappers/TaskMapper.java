package com.capstone.tasks.mappers;

import com.capstone.tasks.domain.dto.TaskDto;
import com.capstone.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
