package com.taskManager.TodoExample.repositories;

import com.taskManager.TodoExample.model.Todo;
import com.taskManager.TodoExample.model.TodoStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByTodoStatus(TodoStatus todoStatus);

    List<Todo> findAllByOrderByIdDesc();

}
