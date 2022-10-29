<script setup>
import router from "../router";
import { useQueryClient, useQuery, useMutation } from "@tanstack/vue-query";
import axios from "axios";
const queryClient = useQueryClient();
const apiAddress = "http://localhost:8080/api/v1/todo/";

const handleSubmitForm = useMutation(
  (newTask) => axios.post(apiAddress, newTask),
  {
    onSuccess: () => {
      queryClient.invalidateQueries("todos");
      router.push("/");
      console.log("lololo");
    },
  }
);

const onHandleSubmitForm = (event) => {
  event.preventDefault();

  const newTask = {
    title: event.target.title.value,
    description: event.target.description.value,
  };
  handleSubmitForm.mutate(newTask);
};
</script>
<template>
  <main class="mt-8">
    <h2>Criar nova task</h2>
    <form class="mt-4 flex flex-col gap-4" @submit="onHandleSubmitForm">
      <label for="title">Título</label>
      <input
        id="title"
        name="title"
        type="text"
        required
        placeholder="Digite aqui um título para a task"
        class="w-full p-2 rounded bg-gray-800 text-white"
        v-model="title"
      />
      <label for="description">Descrição</label>
      <textarea
        id="description"
        name="description"
        required
        placeholder="Digite aqui uma descrição para a task"
        class="w-full p-2 rounded bg-gray-800 text-white"
        v-model="description"
      />
      <button class="bg-blue-500 px-4 p-2 rounded w-full">Save</button>
    </form>
  </main>
</template>
