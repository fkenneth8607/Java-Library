package com.wegot.library.service;

  
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.wegot.library.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

   
    @InjectMocks
    BookService underTest;

}