package com.wegot.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wegot.library.dto.BookDTO;
import com.wegot.library.entity.Book;
import com.wegot.library.service.BookService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {
	
	private Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService ;
	
	
	@GetMapping("/books")
	public ResponseEntity<Object> getAllBooks(){
		try {
			Iterable<Book> books = bookService.findAll();
			return new ResponseEntity<Object>(books, HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Object> getBookById(@PathVariable("id") Long id) {
		try {
			BookDTO book = bookService.findById(id);
			if(book != null) {
				return new ResponseEntity<Object>(book, HttpStatus.OK);				
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/books")
	public ResponseEntity<Object> createBook(@RequestBody BookDTO book) {
		try {
			Long savedBook = bookService.add(book);
			return new ResponseEntity<Object>("Book added Succefully!" , HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<>(
					  ex.getMessage(), 
			          HttpStatus.BAD_REQUEST);
			     
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<HttpStatus> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO book) {
		try {
			book.setId(id);
			Long savedBook = bookService.update(book);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable("id") Long id) {
		try {
			bookService.delete(id);
			return new ResponseEntity<Object>("Book Deleted Succefully!", HttpStatus.OK);
		} catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<>(
					  ex.getMessage(), 
			          HttpStatus.BAD_REQUEST);
		}
	}
	
}