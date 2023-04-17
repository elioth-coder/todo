package com.example.todo.person;

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
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping("/person/search")
    public List<Person> search(@RequestParam("q") String q) {
        return personService.search(q);
    }

    @GetMapping("/person")
    public List<Person> list() {
        return personService.findAll();
    }
    
    @GetMapping("/person/{id}")
    public Optional<Person> get(@PathVariable("id") Integer id) {
        return personService.findById(id);
    }

    @PutMapping("/person")
    public JsonResponse update(@RequestBody Person person) {
        System.out.println(person.toString());

        Person savedPerson = personService.save(person);
        JsonResponse response = new JsonResponse();

        if(savedPerson != null) {
            response.status = "success";
            response.message = "Successfully updated the person!";
        } else {
            response.status = "error";
            response.message = "Failed to update the person!";
        }

        return response;
    }

    @PostMapping("/person")
    public JsonResponse create(@RequestBody Person person) {
        System.out.println(person.toString());

        Person savedPerson = personService.save(person);
        JsonResponse response = new JsonResponse();

        if(savedPerson != null) {
            response.status = "success";
            response.message = "Successfully added the person!";
        } else {
            response.status = "error";
            response.message = "Failed to add the person!";
        }

        return response;
    }

    @DeleteMapping("/person/{id}")
    public JsonResponse delete(@PathVariable("id") Integer id) {
        JsonResponse response = new JsonResponse();
        Boolean deleted;
        try {
            personService.deleteById(id);
            deleted = true;
        } catch (IllegalArgumentException e) {
            response.message = "Error: " + e.getMessage();
            deleted = false;
        }

        if(deleted) {
            response.status = "success";
            response.message = "Successfully deleted the person!";
        } else {
            response.status = "error";
        }

        return response;
    }

}
