package com.huangstomach.springalchemy.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.huangstomach.springalchemy.book.repository.BookRepository;
import com.huangstomach.springalchemy.book.entity.Book;

@RestController
@RequestMapping(path="/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> post(@RequestBody Mono<Book> book) {
        return bookRepository.saveAll(book).next();
    }


    @GetMapping(params = "recent")
    public Mono<ServerResponse> recent() {
        return ServerResponse.ok().body(bookRepository.findAll().take(10), Book.class);
    }

    @GetMapping()
    public Iterable<Book> list(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "0") int size
        ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        return bookRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Mono<Book> get(@PathVariable int id) {
        return bookRepository.findById(id);
    }
}
