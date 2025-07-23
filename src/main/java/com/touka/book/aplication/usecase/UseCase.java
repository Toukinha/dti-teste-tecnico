package com.touka.book.aplication.usecase;

@FunctionalInterface
public interface UseCase<I, O> {
    O execute(I input);
}