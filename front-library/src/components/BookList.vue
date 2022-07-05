<template>
    <div>
        <h3>Gestión de Libros</h3>
        <router-link to="/add" class="btn btn-primary mt-4 mb-4">Agregar Nuevo Libro</router-link>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">ISBN</th>
                <th scope="col">Titulo</th>
                <th scope="col">Autor</th>
                <th scope="col">Editorial</th>
                <th scope="col">Numero de Paginas</th>
                <th scope="col" colspan="2">Actions</th>
                </tr>
            </thead>
            <tbody v-for="(book, index) in books" :key="index">
                <tr>
                    <td>{{book.isbn}}</td>
                    <td>{{book.title}}</td>
                    <td>{{book.author}}</td>
                    <td>{{book.editorial}}</td>
                    <td>{{book.page_number}}</td>
                    <td><a :href="'/books/' + book.id" class="btn btn-primary">Editar</a></td>
                    <td><button @click="deleteBook(book.id)" class="btn btn-danger">Eliminar</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import BookDataService from '../services/BookDataService'

export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: 'books',
    data() {
        return {
            books: []
        }
    },
    methods: {
        retrieveBooks() {
            BookDataService.getAll()
                .then(response => {
                    this.books = response.data
                })
                .catch(e => {
                    alert(e)
                })
        },
         deleteBook(id) {
            BookDataService.delete(id)
                .then((response) => {
                    alert(response.data)
                    this.retrieveBooks();
                })
                .catch(e => {
                    alert(e.response.data)
                })
        }
    },
    mounted() {
        this.retrieveBooks()
    }
}
</script>