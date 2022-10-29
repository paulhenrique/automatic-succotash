package com.taskManager.TodoExample.controllers;

import com.taskManager.TodoExample.model.Todo;
import com.taskManager.TodoExample.services.TodoService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/todo")
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping({"/incomplete"})
    public ResponseEntity<List<Todo>> getNotCompleteTodos() {
        List<Todo> todos = todoService.getNotCompleteTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping({"/complete"})
    public ResponseEntity<List<Todo>> getCompletedTodos() {
        List<Todo> todos = todoService.getCompletedTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping({"/{todoId}"})
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId){
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo){
        Todo insertedTodo = todoService.insert(todo);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("todo", "/api/v1/todo/" + insertedTodo.getId().toString());
        return new ResponseEntity<>(insertedTodo, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{todoId}"})
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo todo){
        todoService.updateTodo(todoId, todo);
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PutMapping({"/{todoId}/complete"})
    public ResponseEntity<Todo> updateTodoToComplete(@PathVariable Long todoId){
        todoService.makeTodoCompleted(todoId);
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PutMapping({"/{todoId}/toggle"})
    public ResponseEntity<Todo> toggleTodoCompleted(@PathVariable Long todoId){
        todoService.toggleTodoCompleted(todoId);
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @DeleteMapping({"/{todoId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
