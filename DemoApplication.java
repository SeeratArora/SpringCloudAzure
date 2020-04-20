package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

public class Todo {

    @Id
    public String id;

    public String description;

    public boolean done;

    public Todo() {
    }

    public Todo(String id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }
}
public interface TodoRepository extends MongoRepository<Todo, String> {

}@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostConstruct
    public void init() {
        todoRepository.saveAll(Arrays.asList(
                new Todo("1", "First item", true),
                new Todo("2", "Second item", true),
                new Todo("3", "Third item", false)));
    }

    @GetMapping("/")
    public @ResponseBody
    List<Todo> showAllTodos() {
        return todoRepository.findAll();
    }
}
