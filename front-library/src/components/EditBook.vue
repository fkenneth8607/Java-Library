<template>
    <div v-if="currentBook">
        <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" class="form-control" id="isbn" required name="isbn" v-model="currentBook.isbn">
            </div>
            <div class="mb-3">
                <label for="title" class="form-label">Titulo</label>
                <input type="text" class="form-control" id="title" required name="title" v-model="currentBook.title">
            </div>
            <div class="mb-3">
                <label for="author" class="form-label">Autor</label>
                <input type="text" class="form-control" id="author" required name="author" v-model="currentBook.author">
            </div>
            <div class="mb-3">
                <label for="editorial" class="form-label">Editorial</label>
                <input type="text" class="form-control" id="editorial" required name="editorial" v-model="currentBook.editorial">
            </div>
             <div class="mb-3">
                <label for="page_number" class="form-label">Numero de Paginas</label>
                <input type="number" class="form-control" id="page_number" required name="page_number" v-model="currentBook.page_number">
            </div>
            <div class="mb-3">
                <button @click="updateBook" class="btn btn-primary me-3">Actualizar</button>
                <button @click="deleteBook" class="btn btn-danger">Eliminar</button>
            </div>
             <div class="alert alert-success" role="alert" v-if="message">
                {{message}}
            </div>
    </div>
</template>

<script>
import BookDataService from '../services/BookDataService'

export default {
    name: 'edit-book',
    data() {
        return {
            currentBook: null,
            message: ''
        }
    },
    methods: {
        getBook(id) {
            BookDataService.get(id)
                .then(response => {
                    this.currentBook = response.data
                })
                .catch(e => {
                    alert(e)
                })
        },
        updateBook() {
            BookDataService.update(this.currentBook.id, this.currentBook)
                .then(() => {
                    this.message = 'The book was updated successfully!'
                })
                .catch(e => {
                    alert(e)
                })
        },
        deleteBook() {
            BookDataService.delete(this.currentBook.id)
                .then(() => {
                    this.$router.push({name: 'books'})
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.getBook(this.$route.params.id)
    }
}
</script>