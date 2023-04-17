package com.example.todo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.JsonResponse;

@RestController
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @GetMapping("/task/search")
    public List<Task> search(@RequestParam("q") String q) {
        List<Task> task = taskService.search(q);

        return task;
    }

    @GetMapping("/task/{id}")
    public Optional<Task> get(@PathVariable("id") String id) {
        Optional<Task> task = taskService.findById(Integer.parseInt(id));

        return task;
    }

    @GetMapping("/task")
    public List<Task> index() {
        List<Task> tasks = taskService.findAll();

        return tasks;
    }

    @DeleteMapping("/task/{id}")
    public JsonResponse delete(@PathVariable("id") String id) {
        JsonResponse response = new JsonResponse();
        Boolean deleted;
        try {
            taskService.deleteById(Integer.parseInt(id));
            deleted = true;
        } catch (IllegalArgumentException e) {
            response.message = "Error: " + e.getMessage();
            deleted = false;
        }

        if(deleted) {
            response.status = "success";
            response.message = "Successfully deleted the task!";
        } else {
            response.status = "error";
        }

        return response;
    }

    @PutMapping("/task")
    public JsonResponse update(@RequestBody Task task) {

        Task savedTask = taskService.save(task);
        JsonResponse response = new JsonResponse();

        if(savedTask != null) {
            response.status = "success";
            response.message = "Successfully updated the task!";
        } else {
            response.status = "error";
            response.message = "Failed to update the task!";
        }

        return response;
    }

    @PostMapping("/task")
    public JsonResponse insert(@RequestBody Task task) {

        Task savedTask = taskService.save(task);
        JsonResponse response = new JsonResponse();

        if(savedTask != null) {
            response.status = "success";
            response.message = "Successfully added the task!";
        } else {
            response.status = "error";
            response.message = "Failed to add the task!";
        }

        return response;
    }
}
