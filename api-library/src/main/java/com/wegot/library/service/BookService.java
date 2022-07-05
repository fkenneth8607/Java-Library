package com.wegot.library.service;
 
import com.wegot.library.dto.BookDTO;
import com.wegot.library.entity.Book;
import com.wegot.library.exception.BookException;

public interface BookService {
	
	public BookDTO findById(Long id) throws BookException ;

	public Iterable<Book> findAll() throws BookException ;

	public Long add(BookDTO book) throws BookException ;
	
	public Long update(BookDTO book) throws BookException ;
	
	public void delete(Long id) throws BookException ;
	

}