package com.example.todo.person;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public List<Person> search(String q) {
        return personRepository.findByFirstNameContainingOrLastNameContaining(q, q);
    }
}
