import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/books',
            name: 'books',
            component: () => import('./components/BookList')
        },
        {
            path: '/books/:id',
            name: 'edit-book',
            component: () => import('./components/EditBook')
        },
        {
            path: '/add',
            name: 'add-book',
            component: () => import('./components/AddBook')
        }
    ]
})