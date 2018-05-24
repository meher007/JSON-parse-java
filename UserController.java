package surreyjson.jsondb.controller;

import surreyjson.jsondb.domain.User;
import surreyjson.jsondb.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/list")
    public Iterable<User> list() {
        return userService.list();
    }

}