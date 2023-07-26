package com.huangstomach.springalchemy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

// import com.huangstomach.springalchemy.orm.Book;
import com.huangstomach.springalchemy.orm.User;
import com.huangstomach.springalchemy.orm.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User post(@RequestBody User user) {
        // Book book = new Book("一人一本书");
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<User> get(@PathVariable int id, @Value("${custom.player.name}") String name) {
        Optional<User> res = userRepository.findById(id);
        if (!res.isPresent()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        User user = res.get();
        user.setName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public Iterable<User> list() {
        PageRequest page = PageRequest.of(0, 10, Sort.by("id").descending());
        return userRepository.findAll(page).getContent();
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
