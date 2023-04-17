package com.example.todo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return (List<Task>) taskRepository.findAll();
    }

    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAllByPersonId(Integer id) {
        return taskRepository.findAllByPersonId(id);
    }

    public List<Task> findByTaskContaining(String query) {
        return taskRepository.findByTaskContaining(query);
    }

    public List<Task> search(String q) {
        return taskRepository.findByPersonFirstNameContaining(q);
    }
}
