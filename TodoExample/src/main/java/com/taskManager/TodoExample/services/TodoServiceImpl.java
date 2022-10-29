package com.taskManager.TodoExample.services;

import com.taskManager.TodoExample.model.Todo;
import com.taskManager.TodoExample.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;

    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public Todo insert(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDB = todoRepository.findById(id).get();
        System.out.println(todoFromDB.toString());
        todoFromDB.setTodoStatus(todo.getTodoStatus());
        todoFromDB.setTitle(todo.getTitle());
        todoRepository.save(todoFromDB);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}