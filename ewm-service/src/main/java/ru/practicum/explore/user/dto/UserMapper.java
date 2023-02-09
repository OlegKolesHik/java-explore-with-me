package ru.practicum.explore.user.dto;

import ru.practicum.explore.user.model.User;

public class UserMapper {
    public static UserShortDto toUserShortDto(User user) {
        if (user == null) return null;
        else return new UserShortDto(
                user.getId(),
                user.getName()
        );
    }

    public static UserDto toUserDto(User user) {
        if (user == null) return null;
        else return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User toUser(NewUserRequest newUserRequest) {
        if (newUserRequest == null) return null;
        else return new User(
                0L,
                newUserRequest.getName(),
                newUserRequest.getEmail(),
                null
        );
    }
}
