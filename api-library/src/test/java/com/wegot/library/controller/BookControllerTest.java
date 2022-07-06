package com.wegot.library.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
 
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wegot.library.dto.BookDTO;
import com.wegot.library.entity.Book;
import com.wegot.library.exception.BookException;
import com.wegot.library.service.BookService;

@RunWith(SpringRunner.class)
public class BookControllerTest {
	    @Mock //Test 
	    BookService mockBookService;

	    @InjectMocks //Mock  
	    BookController underTest;
 
	    @Before
	    public void setUp() throws IOException {
	        MockitoAnnotations.openMocks(this);
	    }
	    	    
	    @Test
	    public void getById_shouldReturnBookDTO() throws BookException{

	    	   BookDTO bookDto = new BookDTO();
		       bookDto.setTitle("test");
		       bookDto.setEditorial("test");
		       bookDto.setPage_number(1L);
		       bookDto.setAuthor("test");
		       bookDto.setIsbn("test");

		       //given
		        when(mockBookService.findById(anyLong())).thenReturn(bookDto);
		        
		        //when
		        ResponseEntity<Object> response = underTest.getBookById(1L);
		        
		        //then
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK,response.getStatusCode());
		        assertNotNull(response.getBody());
		        assertNotNull(((BookDTO)response.getBody()).getAuthor());
		        assertEquals(((BookDTO)response.getBody()).getAuthor(), bookDto.getAuthor());

	    }

	    @Test
	    public void getById_shouldReturnNull() throws BookException{

		       //given
		        when(mockBookService.findById(anyLong())).thenReturn(null);
		        
		        //when
		        ResponseEntity<Object> response = underTest.getBookById(1L);
		        
		        //then
		        assertNotNull(response);
		        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
		        assertNull(response.getBody());

	    }
	    
	    @Test
	    public void getById_shouldCathException() throws BookException{

	    	BookException ex = new BookException("Error");
		       //given
		        when(mockBookService.findById(anyLong())).thenThrow(ex);
		        
		        //when
		        ResponseEntity<Object> response = underTest.getBookById(1L);
		        
		        //then
		        assertNotNull(response);
		        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		        assertNull(response.getBody());

	    }
	    
	    
	    @Test
	    public void getAll_shouldReturnListBookDTO() throws BookException{

	    	   Book book = new Book();
	    	   book.setTitle("test");
	    	   book.setEditorial("test");
	    	   book.setPage_number(1L);
	    	   book.setAuthor("test");
		       book.setIsbn("test");
		       Iterable<Book> bookList = Collections.singletonList(book);
		       
		       
		       //mocking findAll method
		        when(mockBookService.findAll()).thenReturn(bookList);
 
		        //when
		        ResponseEntity<Object> response = underTest.getAllBooks();
		        
		        //then
		        assertNotNull(response);
		       
	    }
	    
	    
	    @Test
	    public void saveBook_shouldReturnOkResult() throws BookException{

	    	   BookDTO bookDTO = new BookDTO();
	    	   bookDTO.setTitle("test");
	    	   bookDTO.setEditorial("test");
	    	   bookDTO.setPage_number(1L);
	    	   bookDTO.setAuthor("test");
	    	   bookDTO.setIsbn("test");
 

	    	   Book book = new Book();
	    	   book.setTitle("test");
	    	   book.setEditorial("test");
	    	   book.setPage_number(1L);
	    	   book.setAuthor("test");
		       book.setIsbn("test");
		       
		       //mocking save
		        when(mockBookService.add(bookDTO)).thenReturn(book);
 
		        //when
		        ResponseEntity<Object> response = underTest.createBook(bookDTO);
		        
		        //then
		        assertNotNull(response);
		        assertEquals(HttpStatus.CREATED,response.getStatusCode());
		        assertNotNull(response.getBody()); 
		       
	    }

	    
	    @Test
	    public void updateBook_shouldReturnOkResult() throws BookException{

	    	   BookDTO bookDTO = new BookDTO();
	    	   bookDTO.setTitle("test");
	    	   bookDTO.setEditorial("test");
	    	   bookDTO.setPage_number(1L);
	    	   bookDTO.setAuthor("test");
	    	   bookDTO.setIsbn("test");
 

	    	   Book book = new Book();
	    	   book.setTitle("test");
	    	   book.setEditorial("test");
	    	   book.setPage_number(1L);
	    	   book.setAuthor("test");
		       book.setIsbn("test");
		       
		       //mocking update
		        when(mockBookService.update(bookDTO)).thenReturn(book);
 
		        //when
		        ResponseEntity<Object> response = underTest.updateBook(bookDTO.getId(),bookDTO);
		        
		        //then
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK,response.getStatusCode());
		        assertNotNull(response.getBody()); 
		       
	    }
	    

	    @Test
	    public void deleteBook_shouldReturnOkResult() throws BookException{

	    	   BookDTO bookDTO = new BookDTO();
	    	   bookDTO.setTitle("test");
	    	   bookDTO.setEditorial("test");
	    	   bookDTO.setPage_number(1L);
	    	   bookDTO.setAuthor("test");
	    	   bookDTO.setIsbn("test");
  
		        
		        //when
		        ResponseEntity<Object> response = underTest.deleteBook(bookDTO.getId());
		        
		        //then
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK,response.getStatusCode()); 
		       
	    }

	      
}
