package com.itsync.controller;

import com.itsync.api.BooksApi;
import com.itsync.entity.Book;
import com.itsync.model.BookModel;
import com.itsync.model.ResponseModel;
import com.itsync.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<ResponseModel> getAllBooks(String bookId, final Pageable pageable) {
        Page<Book> bookList = bookRepository.findAll(pageable);
        return ResponseEntity.ok(ResponseModel.builder().data(bookList).totalItems(bookList.getTotalElements()).build());
    }

    @Override
    public ResponseEntity<ResponseModel> addBook(BookModel bookModel) {
        Book book = new Book();
        BeanUtils.copyProperties(bookModel, book);
        Book newBook = bookRepository.save(book);
        return ResponseEntity.ok(ResponseModel.builder().data(newBook).build());
    }

    @Override
    public ResponseEntity<ResponseModel> updateBook(BookModel bookModel) {
        Book book = new Book();
        BeanUtils.copyProperties(bookModel, book);
        try {
            Book newBook = bookRepository.save(book);
            return ResponseEntity.ok(ResponseModel.builder().data(newBook).build());
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ResponseModel> deleteBookById(Long bookId) {
        try {
            bookRepository.deleteById(bookId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ResponseModel> fetchVolumesGreaterThanFour(Integer volumes, final Pageable pageable) {
        List<Book> bookList = bookRepository.findByVolumesGreaterThan(4);
        return ResponseEntity.ok(ResponseModel.builder().data(bookList).totalItems(Long.valueOf(bookList.size())).build());
    }

    @Override
    public ResponseEntity<ResponseModel> fetchByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthorContainingIgnoreCase(author);
        return ResponseEntity.ok(ResponseModel.builder().data(bookList).totalItems(Long.valueOf(bookList.size())).build());
    }
}

