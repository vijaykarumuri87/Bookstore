package com.itsync.controller;

import com.itsync.api.BooksApi;
import com.itsync.entity.Book;
import com.itsync.model.ResponseModel;
import com.itsync.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<ResponseModel> getAllBooks(String bookId,  final Pageable pageable) {
        List<Book> bookList =  bookRepository.findAll();
        return  ResponseEntity.ok(ResponseModel.builder().data(bookList).build());
    }
}

