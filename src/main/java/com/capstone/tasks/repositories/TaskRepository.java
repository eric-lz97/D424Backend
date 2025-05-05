package com.capstone.tasks.repositories;

import com.capstone.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface  TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);
    void deleteByTaskListIdAndId(UUID taskListId, UUID id);

    // search function
    List<Task> findByTaskListIdAndTitleContainingIgnoreCaseOrTaskListIdAndDescriptionContainingIgnoreCase(
            UUID taskListId1, String title,
            UUID taskListId2, String description
    );
}
