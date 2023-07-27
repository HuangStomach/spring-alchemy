package com.huangstomach.springalchemy.user.controller;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.reactive.function.client.WebClient;

import com.huangstomach.springalchemy.user.entity.User;
import com.huangstomach.springalchemy.user.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User post(@RequestBody User user) {
        // Book book = new Book("一人一本书");
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> get(@PathVariable int id, @Value("${custom.player.name}") String name) {
        Optional<User> res = userRepository.findById(id);
        if (!res.isPresent()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        User user = res.get();
        user.setName(name);

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES))
            .eTag(user.getEmail())
            .body(user);
    }
    
    @GetMapping(path = "/{id}", params = "proxy")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> get(@PathVariable int id) {
        WebClient client = WebClient.create("http://localhost:8080");
        ResponseEntity<User> result = client.get()
            .uri("/user/{id}", id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(User.class).block();
        User user = result.getBody();

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES))
            .eTag(user == null ? "" : user.getEmail())
            .body(user);
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> list(
        @RequestParam(defaultValue = "0") int page, 
        @RequestParam(defaultValue = "10") int size
        ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        try {
            Iterable<User> users = userRepository.findAll(pageRequest).get().getContent();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping(params = "async")
    public DeferredResult<Iterable<User>> async() {
        DeferredResult<Iterable<User>> deferredResult = new DeferredResult<>();
        return deferredResult; // 从其他地方返回值 使用 deferredResult.setResult();
        // deferredResult.setResult(
        //     userRepository.findAll()
        // );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patch(@PathVariable int id, @RequestBody User body) {
        Optional<User> res = userRepository.findById(id);
        if (!res.isPresent()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        User user = res.get();
        if (body.getName() != null) user.setName(body.getName());
        if (body.getEmail() != null) user.setEmail(body.getEmail());
        
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<User> res = userRepository.findById(id);
        if (!res.isPresent()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        User user = res.get();
        userRepository.deleteById(user.getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
