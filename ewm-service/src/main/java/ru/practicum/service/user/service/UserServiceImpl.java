package ru.practicum.service.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.service.exception.ConflictEx;
import ru.practicum.service.exception.NotFoundEx;
import ru.practicum.service.user.dto.NewUserRequest;
import ru.practicum.service.user.dto.UserDto;
import ru.practicum.service.user.dto.UserMapper;
import ru.practicum.service.user.model.User;
import ru.practicum.service.user.repository.UserRepositoryJpa;
import ru.practicum.service.validation.Validation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryJpa repository;
    private final Validation validation;

    @Override
    public List<UserDto> getAllUsers(Long[] ids, int from, int size) {
        validation.validatePagination(from, size);
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);
        List<User> users = (ids == null) ? repository.findAll(pageable).stream().collect(Collectors.toList())
                : repository.findAllById(Arrays.asList(ids));
        return users.stream()
                .map(p -> UserMapper.toUserDto(p))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> createUser(NewUserRequest newUserRequest) {
        if (repository.findAllByName(newUserRequest.getName()) > 0) {
            throw new ConflictEx(newUserRequest.getName(), "users");
        }
        return Optional.ofNullable(UserMapper.toUserDto(repository.save(UserMapper.toUser(newUserRequest))));
    }

    @Override
    public void deleteUserById(long id) throws NotFoundEx {
        validation.validateUser(id);
        repository.deleteById(id);
    }
}
