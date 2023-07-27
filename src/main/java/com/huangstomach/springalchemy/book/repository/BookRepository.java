package com.huangstomach.springalchemy.book.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.huangstomach.springalchemy.book.entity.Book;

import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
    // Flux<Book> findAll(Pageable pageable);
}
