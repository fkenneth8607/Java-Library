package com.wegot.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.wegot.library.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}