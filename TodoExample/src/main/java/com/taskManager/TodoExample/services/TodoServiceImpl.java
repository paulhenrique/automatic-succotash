package com.taskManager.TodoExample.services;

import com.taskManager.TodoExample.model.Todo;
import com.taskManager.TodoExample.model.TodoStatus;
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
    public List<Todo> getNotCompleteTodos() {
        return todoRepository.findByTodoStatus(TodoStatus.NOT_COMPLETED);
    }

    @Override
    public List<Todo> getCompletedTodos(){
        return todoRepository.findByTodoStatus(TodoStatus.COMPLETED);
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
    public void makeTodoCompleted(Long id) {
        Todo todoFromDB = todoRepository.findById(id).get();
        todoFromDB.setTodoStatus(TodoStatus.COMPLETED);
        todoRepository.save(todoFromDB);
    }

    @Override
    public void toggleTodoCompleted(Long id) {
        Todo todoFromDB = todoRepository.findById(id).get();
        if(todoFromDB.getTodoStatus() == TodoStatus.COMPLETED){
            todoFromDB.setTodoStatus(TodoStatus.NOT_COMPLETED);
        }else{
            todoFromDB.setTodoStatus(TodoStatus.COMPLETED);
        }
        todoRepository.save(todoFromDB);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
