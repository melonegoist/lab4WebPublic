<script setup>
import Header from './components/Header.vue';
import AuthForm from './components/AuthForm.vue';
import GraphForm from './components/GraphForm.vue';
import Graph from './components/Graph.vue';
import { onMounted } from 'vue';
import { status, errorMessage, userLogin, token, clearToken } from "@/authState";
import { allDotsJson } from './graphState';


onMounted(async () => {
  if (token) {
    const response = await fetch("http://localhost:8081/authenticate", {
      method: "POST",
      headers: {
        "Authorization": `Bearer ${token.value}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(token.value),
    })

    if (!response.ok) {
        clearToken();
      }

  }
})
</script>

<template>
    <Header>
        <template #h_date>today</template>
        <template #h_name>melon_egoist</template>
        <template #h_group>P3216</template>
    </Header>

    <div v-if="!status">
      <h1 id="welcome-label">Welcome, log in to use lab4</h1>
      <div class="space"></div>
      <AuthForm></AuthForm> 

      <div v-if="errorMessage">
        <h2 id="errorMessage">{{ errorMessage }}</h2>
      </div>
    </div>

    <div v-else>
      <h2 id="welcome-user-label">Welcome, {{ userLogin }}</h2>

      <div class="space"></div>

      <GraphForm>
        <template #x-input-label>Set X value in range(-3, 3):</template>
        <template #y-input-label>Set Y value in range(-2, 5):</template>
        <template #r-input-label>Set R value in range(1, 5):</template>
      </GraphForm>

      <div class="space"></div>

      <Graph />

      <div id="dashboard" v-if="allDotsJson">
        <h4>==OWNER=====X=====Y=====R=====inArea?==</h4>
        <p v-for="el in allDotsJson">{{ `${el.owner} |||| ${el.x} |||| ${el.y} |||| ${el.r} |||| ${el.inArea}`}}</p>
      </div>
    </div>


</template>

<script>
export default {
  data() {
    return {
      users: [],
    };
  },

  name: "Start",

  methods: {
    resizeHeader() {
        const h_name = document.getElementById("name");
        const h_date = document.getElementById("date");
  
        let dateWidth = h_date.clientWidth;
        h_name.style.cssText = `margin-right: ${dateWidth-45}px;`;
    }
  },
};
</script>

<style scoped>
    .space {
        margin-top: 100px;
        margin-bottom: 100px;
    }

    #welcome-label {
      color:darkcyan;
      text-align: center;
    }

    #errorMessage {
      text-align: center;
      color: brown
    }

    #welcome-user-label {
      text-align: center;
      color: whitesmoke
    }

    #graph-response {
      color: goldenrod
    }

    #dashboard {
      color: bisque;
    }

    h4 {
      color: whitesmoke;
      font-weight: bolder;
    }
</style>
