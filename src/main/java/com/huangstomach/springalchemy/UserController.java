package com.huangstomach.springalchemy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.ServerResponse;

import com.huangstomach.springalchemy.orm.Book;
import com.huangstomach.springalchemy.orm.User;
import com.huangstomach.springalchemy.orm.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String post(@RequestParam String name, @RequestParam String email) {
        // Book book = new Book("一人一本书");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Success";
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public User get(@PathVariable int id, @Value("${custom.player.name}") String name) {
        Optional<User> res = userRepository.findById(id);
        if (!res.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "My error message");
        User user = res.get();
        user.setName(name);
        return user;
    }

    @GetMapping()
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ServerResponse delete(@PathVariable int id) {
        Optional<User> res = userRepository.findById(id);
        if (!res.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "My error message");
        User user = res.get();
        userRepository.delete(user);
        return ServerResponse.ok().build();
    }
}
