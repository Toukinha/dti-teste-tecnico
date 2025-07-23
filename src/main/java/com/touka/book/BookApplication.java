package com.touka.book;

import com.touka.book.infrastructure.cli.BookCli;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {

	private final BookCli cli;

	public BookApplication(BookCli cli) {
		this.cli = cli;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Override
	public void run(String... args) {
		cli.start();
	}
}