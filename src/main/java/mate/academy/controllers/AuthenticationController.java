package mate.academy.controllers;

import mate.academy.model.dto.request.UserRequestDto;
import mate.academy.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto dto) {
        authenticationService.register(dto.getEmail(), dto.getPassword());
    }
}
