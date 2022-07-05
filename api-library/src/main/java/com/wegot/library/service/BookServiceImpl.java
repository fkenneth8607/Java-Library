package com.wegot.library.service;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegot.library.dto.BookDTO;
import com.wegot.library.entity.Book;
import com.wegot.library.exception.BookException;
import com.wegot.library.repository.BookRepository;

@Service(value = "bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;


	@Override
	public BookDTO findById(Long bookId) throws BookException {

		Optional<Book> optional = bookRepository.findById(bookId);

		Book book = optional.orElseThrow( () -> new BookException("Book Not Exists!"));

		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setEditorial(book.getEditorial());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setPage_number(book.getPage_number());

		return bookDTO;

	}

	@Override
	public Iterable<Book> findAll() throws BookException {

		 
		Iterable<Book> searchResult = bookRepository.findAll() ;
 
		return searchResult ;
	}

	@Override
	public Book add(BookDTO bookDto) throws BookException {

		if (isValid(bookDto)) {
			Book book = new Book();
			book.setId(bookDto.getId());
			book.setAuthor(bookDto.getAuthor());
			book.setTitle(bookDto.getTitle());
			book.setEditorial(bookDto.getEditorial());
			book.setIsbn(bookDto.getIsbn());
			book.setPage_number(bookDto.getPage_number());

			bookRepository.save(book);
			return book;
		}else {
			return null;
		}

	}

	@Override
	public Book update(BookDTO bookDto) throws BookException {

		if (isValid(bookDto)) {
			Book book = new Book();
			book.setId(bookDto.getId());
			book.setAuthor(bookDto.getAuthor());
			book.setTitle(bookDto.getTitle());
			book.setEditorial(bookDto.getEditorial());
			book.setIsbn(bookDto.getIsbn());
			book.setPage_number(bookDto.getPage_number());

			bookRepository.save(book);
			return book;
		}else {
			return null;
		}


	}

	@Override
	public void delete(Long id) throws BookException {

		bookRepository.deleteById(id);

	}

	//we validate input data and return message to front end
	public boolean isValid(BookDTO bookDto) throws BookException {
		boolean valid = true;


		if (bookDto.getAuthor().isEmpty()) {
			valid = false;
			throw new BookException("Author cant'be empty");
		}

		if (bookDto.getEditorial().isEmpty()) {
			valid = false;
			throw new BookException("Editorial cant'be empty");
		}

		if (bookDto.getIsbn().isEmpty()) {
			valid = false;
			throw new BookException("ISBN cant'be empty");
		}

		if (bookDto.getTitle().isEmpty()) {
			valid = false;
			throw new BookException("Title cant'be empty");
		}


		//here can be validate if entity with ISBN exists and others validations

		return valid;

	}

}