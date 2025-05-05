package com.capstone.tasks.mappers;

import com.capstone.tasks.domain.dto.TaskListDto;
import com.capstone.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
