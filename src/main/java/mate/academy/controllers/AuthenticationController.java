package mate.academy.controllers;

import javax.validation.Valid;
import mate.academy.model.dto.UserRegistrationDto;
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
    public void register(@RequestBody @Valid UserRegistrationDto dto) {
        authenticationService.register(dto.getEmail(), dto.getPassword());
    }
}
