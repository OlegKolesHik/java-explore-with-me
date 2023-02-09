package ru.practicum.service.user.service;

import ru.practicum.service.exception.NotFoundEx;
import ru.practicum.service.user.dto.NewUserRequest;
import ru.practicum.service.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers(Long[] ids, int from, int size);

    Optional<UserDto> createUser(NewUserRequest newUserRequest);

    void deleteUserById(long id) throws NotFoundEx;
}
