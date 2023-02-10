package ru.practicum.explore.user.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.exception.NotFoundEx;
import ru.practicum.explore.user.dto.NewUserRequest;
import ru.practicum.explore.user.service.UserService;

import ru.practicum.explore.user.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/admin/users",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AdminUserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value = "ids", required = false) Long[] ids,
            @PositiveOrZero @RequestParam(value = "from", defaultValue = "0") Integer from,
            @Positive @RequestParam(value = "size", defaultValue = "10") Integer size) {
        log.info("Запрошены все пользователи");
        return new ResponseEntity<>(userService.getAllUsers(ids, from, size), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        log.info("Добавление пользователя {}", newUserRequest);
        return userService.createUser(newUserRequest).map(newUser -> new ResponseEntity<>(newUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) throws NotFoundEx {
        log.info("Удаление пользователя {}", id);
        userService.deleteUserById(id);
    }
}
