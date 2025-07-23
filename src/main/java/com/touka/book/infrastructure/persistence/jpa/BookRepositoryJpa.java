package com.touka.book.infrastructure.persistence.jpa;

import com.touka.book.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJpa extends JpaRepository<Book, Integer> {}
