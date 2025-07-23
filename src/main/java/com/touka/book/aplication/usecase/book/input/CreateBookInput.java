package com.touka.book.aplication.usecase.book.input;

import java.time.LocalDate;

public record CreateBookInput(
        String title,

        String author,

        Integer pages,

        LocalDate publishedDate
) {
}
