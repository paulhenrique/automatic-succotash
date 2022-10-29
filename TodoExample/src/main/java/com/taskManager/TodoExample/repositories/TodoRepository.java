package com.taskManager.TodoExample.repositories;

import com.taskManager.TodoExample.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
