package com.wegot.library.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wegot.library.dto.BookDTO;
import com.wegot.library.entity.Book;
import com.wegot.library.exception.BookException;
import com.wegot.library.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

   
    @InjectMocks
    BookService underTest;

    @Test
    void create_shouldCreateBook() throws BookException{

        //given
        Book expected = new Book();
        expected.setTitle("test");
        expected.setEditorial("test");
        expected.setPage_number(1L);
        expected.setAuthor("test");
        expected.setIsbn("test");

        BookDTO request = new BookDTO();

 
        //mocking save method
        when(bookRepository.save(any())).thenReturn(expected);

        //when
        Book actual = underTest.add(request);

        //then
        assertAll(
                ()->assertNotNull(actual),
                ()->assertEquals(expected,actual),
                ()->assertEquals(expected.getTitle(),actual.getTitle()),
                ()->assertEquals(expected.getEditorial(),actual.getEditorial())
        );
    }

 
    @Test
    void getAll_itShouldReturnBookDTOList() throws BookException{

        //given
        Book book = new Book();
        book.setTitle("test");
        book.setEditorial("test");
        book.setPage_number(1L);
        book.setAuthor("test");
        book.setIsbn("test");
        List<Book> bookList = Collections.singletonList(book);

        BookDTO response = new BookDTO();
        response.setTitle(book.getTitle());
        response.setEditorial(book.getEditorial());
        response.setPage_number(book.getPage_number());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn()); 

        //mocking fidnAll method
        when(bookRepository.findAll()).thenReturn(bookList);

        //when
        List<Book> actual =  StreamSupport.stream(underTest.findAll().spliterator(), false)
          .collect(Collectors.toList());

        //then
        assertEquals(book.getTitle(),actual.get(0).getTitle());
        assertEquals(book.getEditorial(),actual.get(0).getEditorial());

    }

    @Test
    void getById_itShouldReturnBookDTO() throws BookException{

        //given
        Book book = new Book();
        book.setTitle("test");
        book.setEditorial("test");
        book.setPage_number(1L);
        book.setAuthor("test");
        book.setIsbn("test");

        BookDTO response = new BookDTO();
        response.setTitle(book.getTitle());
        response.setEditorial(book.getEditorial());
        response.setPage_number(book.getPage_number());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn()); 

        when(bookRepository.findById(anyLong())).
                thenReturn(Optional.of(book));

        //when
        BookDTO actual = underTest.findById(1L);

        //then
        assertEquals(response,actual);
    }

    @Test
    void getById_itShouldThrowNotFound_whenBookIdNotFound(){

        //given
        when(bookRepository.findById(anyLong())).
                thenReturn(Optional.empty());
 
    }

    @Test
    void deleteById_itShouldReturnStatusNotFound_whenBookIdNotExist(){

        //given
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
 
    }
}