package com.touka.book.aplication.usecase.book;

import com.touka.book.aplication.usecase.UseCase;
import com.touka.book.aplication.usecase.book.input.UpdateBookInput;
import com.touka.book.domain.model.Book;
import com.touka.book.domain.repository.BookRepositoryInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBookUseCase implements UseCase<UpdateBookInput, Void> {

    private final BookRepositoryInterface bookRepository;

    @Override
    @Transactional
    public Void execute(UpdateBookInput input) {
        Book book = bookRepository.findById(input.id()).orElseThrow();

        book.setTitle(input.title());
        book.setAuthor(input.author());
        book.setPages(input.pages());
        book.setPublishedDate(input.publishedDate());

        bookRepository.save(book);
        return null;
    }
}