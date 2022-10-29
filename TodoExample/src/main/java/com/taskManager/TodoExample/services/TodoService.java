package com.taskManager.TodoExample.services;

import com.taskManager.TodoExample.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();

    List<Todo> getNotCompleteTodos();

    List<Todo> getCompletedTodos();

    Todo getTodoById(Long id);

    Todo insert(Todo todo);

    void updateTodo(Long id, Todo todo);

    void makeTodoCompleted(Long id);

    void deleteTodo(Long id);
}
