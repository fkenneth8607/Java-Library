package com.wegot.library.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
 
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.io.IOException;
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
	      
}
