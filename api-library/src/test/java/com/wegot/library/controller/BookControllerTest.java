package com.wegot.library.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
 
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

 

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wegot.library.dto.BookDTO;
 
import com.wegot.library.exception.BookException;
import com.wegot.library.service.BookService;

public class BookControllerTest {
	    @Mock //Test 
	    BookService mockBookService;

	    @InjectMocks //Mock  
	    BookController underTest;
 
	    @Test
	    void getById_shouldReturnBookDTO() throws BookException{

	        //given
	        BookDTO expected = new BookDTO();
	        expected.setTitle("test");

	        when(mockBookService.findById(anyLong())).thenReturn(expected);

	        //when
	        ResponseEntity<Object> response = underTest.getBookById(1L);
	        BookDTO actual = (BookDTO) response.getBody();

	        //then
	        assertAll(
	                ()->assertNotNull(actual),
	                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
	                ()->assertEquals(expected.getTitle(),actual.getTitle())
	        );
	    }

	    @SuppressWarnings("null")
		@Test
	    void getById_shouldReturnStatusNotFound_whenBookIdNotExist() throws BookException{

	        //given
	        when(mockBookService.findById(anyLong())).thenReturn(null);

	        //when
	        ResponseEntity<Object> response = underTest.getBookById(1L);
	   

	        //then
	        assertNull(response);
	        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

	    }

	    @Test
	    void deleteById_shouldDeleteSuccesfully(){

	        //given
	        //when
	        ResponseEntity<Object> response = underTest.deleteBook(1L);

	        //then
	        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

	    }

	    @Test
	    void deleteById_shouldReturnStatusNotFound_whenBookIdNotExist() throws BookException{

	        //given
	        Mockito.doThrow(EntityNotFoundException.class).
	                when(mockBookService).delete(anyLong());

	        //when
	        ResponseEntity<Object> response = underTest.deleteBook(1L);

	        //then
	        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

	    }
}
