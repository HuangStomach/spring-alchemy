package com.huangstomach.springalchemy.user.cli;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.huangstomach.springalchemy.user.repository.UserRepository;

@ShellComponent
@ShellCommandGroup("user")
public class User {
    @Autowired
    Terminal terminal;
    @Autowired
    UserRepository userRepository;

    @ShellMethod(key = "user list", value = "列出所有用户")
    public void list() {
        for (var user : userRepository.findAll()) {
            terminal.writer().println(user.getName());
        }
    }

    @ShellMethod(key = "user inter", value = "交互")
    public String inter(
        @ShellOption(valueProvider = UserValueProvider.class) 
        String arg
    ) {
        return "You said " + arg;
    }
}
