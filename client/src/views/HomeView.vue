<template>
  <main class="mt-8">
    <h4>All todos:</h4>
    <div id="containerTodos" class="mt-4 flex gap-4 flex-col">
      <div
        class="bg-slate-100 rounded p-4 justify-between flex items-center"
        v-for="todo in todos"
        :key="todo.id"
      >
        <h5
          class="taxed"
          :class="todo.todoStatus === 'COMPLETED' ? 'line-through' : ''"
        >
          {{ todo.title }}
        </h5>
        <button
          class="text-sm p-2 text-white rounded transition-colors"
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
    </div>
  </main>
</template>
<script lang="ts">
import axios from "axios";

export default {
  name: "HomeView",
  data: () => ({
    message: "Hello from HomeView",
    todos: [],
  }),
  mounted() {
    console.log(this.message);
    this.getTodos();
  },
  methods: {
    async getTodos() {
      const { data } = await axios.get("http://localhost:8080/api/v1/todo");
      this.todos = data;
    },
  },
};
</script>
