<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <main class="px-72 p-8">
    <header class="flex justify-between items-center">
      <h1 class="text-4xl">Example Todo List</h1>
      <router-link
        class="bg-blue-500 px-4 p-2 text-white rounded hover:bg-blue-700 transition-colors"
        to="/newTask"
      >
        New Task
      </router-link>
    </header>
    <RouterView />
  </main>
</template>

<style scoped></style>
