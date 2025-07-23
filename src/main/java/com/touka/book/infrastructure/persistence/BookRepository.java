package com.touka.book.infrastructure.persistence;


import com.touka.book.domain.model.Book;
import com.touka.book.domain.repository.BookRepositoryInterface;
import com.touka.book.infrastructure.persistence.jpa.BookRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class BookRepository implements BookRepositoryInterface {

    private final BookRepositoryJpa jpaRepository;

    @Override
    public void save(Book book) {
        jpaRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }

}
