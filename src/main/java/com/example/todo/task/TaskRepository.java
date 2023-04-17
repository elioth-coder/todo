package com.example.todo.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByPersonId(Integer id);

    List<Task> findByTaskContaining(String query);

    List<Task> findByPersonFirstNameContaining(String q);
}
