<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">Gestiòn de Libros</router-link>
        <div class="collapse navbar-collapse">
          <div class="navbar-nav">
            <router-link to="/books" class="nav-link">Lista de Libros</router-link>
          
          </div>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>