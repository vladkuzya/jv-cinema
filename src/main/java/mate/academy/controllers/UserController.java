package mate.academy.controllers;

import mate.academy.model.dto.response.UserResponseDto;
import mate.academy.service.UserService;
import mate.academy.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto byEmail(@RequestParam String email) {
        return userMapper.mapToDto(userService.findByEmail(email).get());
    }
}
