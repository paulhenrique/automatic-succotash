package com.taskManager.TodoExample.bootstrap;

import com.taskManager.TodoExample.model.Todo;
import com.taskManager.TodoExample.model.TodoStatus;
import com.taskManager.TodoExample.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoLoader implements CommandLineRunner {
    public final TodoRepository todoRepository;

    public TodoLoader (TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadTodos();
    }

    private void loadTodos() {
        if(todoRepository.count() == 0){
            todoRepository.save(
                    Todo.builder()
                            .title("Go to market")
                            .description("Alo alo")
                            .todoStatus(TodoStatus.NOT_COMPLETED)
                            .build()
            );

            todoRepository.save(
                    Todo.builder()
                            .title("Go to school")
                            .description("Alo alo")
                            .todoStatus(TodoStatus.NOT_COMPLETED)
                            .build()
            );

            System.out.println("Sample Todos Loaded");
        }
    }


}
