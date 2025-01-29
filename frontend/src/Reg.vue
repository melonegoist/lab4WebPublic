<script setup>
import Header from './components/Header.vue';
import RegForm from './components/RegForm.vue';
</script>

<template>
    <Header>
        <template #h_date>24.01</template>
        <template #h_name>melon_egoist reg</template>
        <template #h_group>P3216</template>
    </Header>

    <div class="space"></div>

    <h1>Registration</h1>

    <div class="space"></div>

    <RegForm></RegForm>
</template>

<style scoped>
    h1 {
        text-align: center;
        color: cadetblue;
    }

    .space {
        margin: 0;
        margin-top: 50px;
        margin-bottom: 50px;
    }
</style>
