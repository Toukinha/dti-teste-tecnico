package com.touka.book.aplication.usecase.book;

import com.touka.book.aplication.usecase.UseCase;
import com.touka.book.aplication.usecase.book.input.CreateBookInput;
import com.touka.book.domain.model.Book;
import com.touka.book.domain.repository.BookRepositoryInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookUseCase implements UseCase<CreateBookInput, Void> {
    private final BookRepositoryInterface bookRepository;

    @Override
    @Transactional
    public Void execute(CreateBookInput input) {
        Book book = Book.builder()
                .title(input.title())
                .author(input.author())
                .pages(input.pages())
                .publishedDate(input.publishedDate())
                .build();
        bookRepository.save(book);
        return null;
    }
}
