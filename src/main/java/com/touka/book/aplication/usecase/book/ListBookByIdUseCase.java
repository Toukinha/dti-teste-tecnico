package com.touka.book.aplication.usecase.book;

import com.touka.book.aplication.usecase.UseCase;
import com.touka.book.domain.model.Book;
import com.touka.book.domain.repository.BookRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListBookByIdUseCase implements UseCase<Integer, Optional<Book>> {

    private final BookRepositoryInterface bookRepository;

    @Override
    public Optional<Book> execute(Integer id) {

        return bookRepository.findById(id);
    }
}
