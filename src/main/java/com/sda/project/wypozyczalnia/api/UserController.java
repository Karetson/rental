package com.sda.project.wypozyczalnia.api;

import com.sda.project.wypozyczalnia.dto.UserForm;
import com.sda.project.wypozyczalnia.model.User;
import com.sda.project.wypozyczalnia.services.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addNewUser(@RequestBody UserForm userForm) {
        User savedUser = userService.addNewUser(userForm);
        return savedUser;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(name = "name", required = false) String name) {
        if (!StringUtils.isEmpty(name)) {
            return userService.getUserByName(name);
        }
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserForm userForm) {
        return userService.updateUserById(id, userForm);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity handleObjectNotFoundException(ObjectNotFoundException onfe) {
        return ResponseEntity.notFound().build();
    }

}
