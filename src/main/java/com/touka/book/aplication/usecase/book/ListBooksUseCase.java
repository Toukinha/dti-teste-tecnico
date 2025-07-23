package com.touka.book.aplication.usecase.book;

import com.touka.book.aplication.usecase.UseCase;
import com.touka.book.domain.model.Book;
import com.touka.book.domain.repository.BookRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListBooksUseCase implements UseCase<Void, List<Book>> {

    private final BookRepositoryInterface bookRepository;

    @Override
    public List<Book> execute(Void unused) {

        return bookRepository.findAll();
    }
}