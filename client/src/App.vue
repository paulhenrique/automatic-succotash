<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <main class="lg:px-60 md:px-20 p-8 bg-gray-700 h-screen text-white">
    <header class="flex justify-between items-center">
      <router-link to="/">
        <h1 class="text-4xl">Todo List</h1>
      </router-link>
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
