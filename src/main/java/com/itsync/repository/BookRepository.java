package com.itsync.repository;

import com.itsync.entity.Book;
import com.itsync.model.ResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorContainingIgnoreCase(String author);

    void deleteById(Long id);

    List<Book> findByVolumesGreaterThan(int i);

    List<Book> findByFormatIgnoreCase(String format);

    default List<Book> findAllEbooks() {
        return findByFormatIgnoreCase("ebook");
    }
}

