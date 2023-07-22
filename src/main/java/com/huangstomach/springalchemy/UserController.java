package com.huangstomach.springalchemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Success";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        User user = userRepository.findById(id);
        // if (user.getId() <= 0) return HttpStatus.NOT_FOUND;
         
        return user;
    }

    @GetMapping()
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable int id) {
        return "123";
    }
}
