package com.touka.book.domain.repository;

import com.touka.book.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryInterface {

    Book save(Book book);

    Optional<Book> findById(Integer id);

    List<Book> findAll();

    void deleteById(Integer id);
}
