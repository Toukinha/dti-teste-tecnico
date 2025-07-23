package com.touka.book.aplication.usecase.book;

import com.touka.book.aplication.usecase.UseCase;
import com.touka.book.domain.repository.BookRepositoryInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookUseCase implements UseCase<Integer, Void> {

    private final BookRepositoryInterface bookRepository;

    @Override
    @Transactional
    public Void execute(Integer id) {
        bookRepository.deleteById(id);
        return null;
    }
}