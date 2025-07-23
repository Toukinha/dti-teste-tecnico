package com.touka.book.infrastructure.cli;

import com.touka.book.aplication.usecase.book.*;
import com.touka.book.aplication.usecase.book.input.CreateBookInput;
import com.touka.book.aplication.usecase.book.input.UpdateBookInput;
import com.touka.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class BookCli {

    private final CreateBookUseCase createBookUseCase;
    private final ListBooksUseCase listBooksUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    private final ListBookByIdUseCase listBookByIdUseCase;

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("""
                        ──────────────MENU───────────────
                        1 - Listar todos os livros
                        2 - Cadastrar livro
                        3 - Atualizar livro
                        4 - Remover livro
                        5 - Consultar livro por ID
                        9 - Sair
                        ──────────────MENU───────────────
                        """);
                switch (scanner.nextLine()) {
                    case "1" -> listAllBooks();
                    case "2" -> createBook(scanner);
                    case "3" -> updateBook(scanner);
                    case "4" -> removeBook(scanner);
                    case "5" -> findBookById(scanner);
                    case "9" -> {
                        return;
                    }
                    default -> System.out.println("Opção inválida!\n");
                }
            }
        }
    }

    private void listAllBooks() {
        List<Book> books = listBooksUseCase.execute(null);
        if (books.isEmpty()) {
            System.out.println("Sem livros cadastrados.\n");
            return;
        }
        books.forEach(book -> System.out.printf("""
                        ─────────────────────────────
                        Id..............: %d
                        Título..........: %s
                        Autor...........: %s
                        Páginas.........: %d
                        Publicado em....: %s
                        ─────────────────────────────
                        """,
                book.getId(), book.getTitle(), book.getAuthor(), book.getPages(),
                (book.getPublishedDate() != null)
                        ? book.getPublishedDate()
                        : "n/d"));
        System.out.println();
    }

    private void createBook(Scanner scanner) {
        CreateBookInput input = readCreateInput(scanner);
        createBookUseCase.execute(input);
        System.out.println("Livro cadastrado!\n");
    }

    private void updateBook(Scanner scanner) {
        Integer id = readValidId(scanner);

        Book current = listBookByIdUseCase.execute(id)
                .orElseThrow();

        System.out.printf("Título [%s]: ", current.getTitle());
        String title = scanner.nextLine().trim();
        title = title.isBlank() ? current.getTitle() : title;

        System.out.printf("Autor  [%s]: ", current.getAuthor());
        String author = scanner.nextLine().trim();
        author = author.isBlank() ? current.getAuthor() : author;

        Integer pages = null;
        while (pages == null) {
            System.out.printf("Páginas [%d] (ENTER p/ manter): ", current.getPages());
            String input = scanner.nextLine().trim();
            if (input.isBlank()) {
                pages = current.getPages();
            } else {
                try {
                    int value = Integer.parseInt(input);
                    if (value < 1) throw new NumberFormatException();
                    pages = value;
                } catch (NumberFormatException ex) {
                    System.out.println("Valor inválido. Digite um número inteiro maior que 0.");
                }
            }
        }

        LocalDate publishedDate;
        while (true) {
            System.out.print("Data de publicação (AAAA-MM-DD) ou ENTER: ");
            String raw = scanner.nextLine().trim();
            if (raw.isBlank()) {
                publishedDate = null;
                break;
            }
            try {
                publishedDate = LocalDate.parse(raw);
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("Formato inválido. Use AAAA-MM-DD (ex.: 2025-07-23).");
            }
        }

        UpdateBookInput updateInput =
                new UpdateBookInput(id, title, author, pages, publishedDate);

        updateBookUseCase.execute(updateInput);
        System.out.println("Atualizado com sucesso!\n");
    }

    private void removeBook(Scanner scanner) {
        Integer id = readValidId(scanner);
        deleteBookUseCase.execute(id);
        System.out.println("Removido!\n");
    }

    private void findBookById(Scanner scanner) {
        while (true) {
            System.out.print("Informe o ID do livro (0 p/ voltar): ");
            String raw = scanner.nextLine().trim();
            if ("0".equals(raw)) return;

            try {
                Integer id = Integer.parseInt(raw);
                Book book = listBookByIdUseCase.execute(id).orElse(null);
                if (book == null) {
                    System.out.println("Livro não encontrado. Tente novamente.\n");
                    continue;
                }
                System.out.printf("""
                                ─────────────────────────────
                                Id..............: %d
                                Título..........: %s
                                Autor...........: %s
                                Páginas.........: %d
                                Publicado em....: %s
                                ─────────────────────────────
                                """,
                        book.getId(), book.getTitle(), book.getAuthor(),
                        book.getPages(),
                        (book.getPublishedDate() != null)
                                ? book.getPublishedDate()
                                : "n/d");
                System.out.println();
                return;
            } catch (NumberFormatException ex) {
                System.out.println("ID deve ser um número inteiro.\n");
            }
        }
    }

    private CreateBookInput readCreateInput(Scanner scanner) {
        String title;
        do {
            System.out.print("Título (obrigatório): ");
            title = scanner.nextLine().trim();
            if (title.isBlank()) System.out.println("Título não pode ficar em branco.");
        } while (title.isBlank());

        String author;
        do {
            System.out.print("Autor (obrigatório): ");
            author = scanner.nextLine().trim();
            if (author.isBlank()) System.out.println("Autor não pode ficar em branco.");
        } while (author.isBlank());

        Integer pages = null;
        while (pages == null) {
            System.out.print("Páginas (obrigatório): ");
            String raw = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(raw);
                if (value < 1) throw new NumberFormatException();
                pages = value;
            } catch (NumberFormatException ex) {
                System.out.println("Valor inválido. Digite um número inteiro maior que 0.");
            }
        }
        LocalDate publishedDate;
        while (true) {
            System.out.print("Data de publicação (AAAA-MM-DD) ou ENTER para deixar em branco: ");
            String raw = scanner.nextLine().trim();
            if (raw.isBlank()) {
                publishedDate = null;
                break;
            }
            try {
                publishedDate = LocalDate.parse(raw);
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("Formato inválido. Use AAAA-MM-DD (ex.: 2025-07-23).");
            }
        }

        return new CreateBookInput(title, author, pages, publishedDate);
    }

    private Integer readValidId(Scanner scanner) {
        while (true) {
            System.out.print("informe o ID: ");
            try {
                Integer id = Integer.parseInt(scanner.nextLine().trim());
                if (listBookByIdUseCase.execute(id).isPresent()) return id;
                System.out.println("ID não encontrado.");
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}
