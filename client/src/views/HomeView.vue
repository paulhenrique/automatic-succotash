<script setup>
import { useQueryClient, useQuery, useMutation } from "@tanstack/vue-query";
import axios from "axios";
const queryClient = useQueryClient();
const apiAddress = "http://localhost:8080/api/v1/todo/";
const { data, isLoading } = useQuery(["todos"], () => axios.get(apiAddress));

const handleToggleTodo = useMutation(
  (id) => axios.put(apiAddress + id + "/toggle"),
  {
    onSuccess: () => {
      queryClient.invalidateQueries("todos");
    },
  }
);

const onHandleToggleTodoButtonClick = (id) => {
  handleToggleTodo.mutate(id);
};
</script>
<template>
  <main class="mt-8 max-h-96 overflow-y-scroll">
    <span v-if="isLoading">Carregando</span>
    <h4>All todos:</h4>
    <div id="containerTodos" class="mt-4 flex gap-4 flex-col">
      <div
        class="bg-gray-800 rounded p-4"
        v-for="todo in data?.data"
        :key="todo.id"
      >
        <div class="justify-between flex items-center">
          <h5
            class="font-bold"
            :class="todo.todoStatus === 'COMPLETED' ? 'line-through' : ''"
          >
            {{ todo.title }}
          </h5>
          <button
            class="text-sm p-2 text-white rounded transition-colors"
            @click="onHandleToggleTodoButtonClick(todo.id)"
            :class="
              todo.todoStatus === 'COMPLETED'
                ? 'bg-red-500 hover:bg-red-600'
                : 'bg-blue-500 hover:bg-blue-600'
            "
          >
            {{
              todo.todoStatus === "COMPLETED"
                ? "Mark as incomplete"
                : "Mark as complete"
            }}
          </button>
        </div>
        <p>{{ todo.description }}</p>
      </div>
    </div>
  </main>
</template>
