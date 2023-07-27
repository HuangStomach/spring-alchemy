package com.huangstomach.springalchemy.book.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.huangstomach.springalchemy.book.entity.Book;

import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
    Flux<Book> findAll();
    // https://stackoverflow.com/questions/58874827/spring-data-r2dbc-and-pagination
    Flux<Book> findBy(Pageable pageable);
}
