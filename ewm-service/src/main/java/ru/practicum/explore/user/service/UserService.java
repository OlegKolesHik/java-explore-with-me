package ru.practicum.explore.user.service;

import ru.practicum.explore.exception.NotFoundEx;
import ru.practicum.explore.user.dto.NewUserRequest;
import ru.practicum.explore.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers(Long[] ids, int from, int size);

    Optional<UserDto> createUser(NewUserRequest newUserRequest);

    void deleteUserById(long id) throws NotFoundEx;
}
