package mate.academy.service.mapper;

import mate.academy.model.User;
import mate.academy.model.dto.request.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToEntity(UserRequestDto dto) {
        User user = new User(dto.getEmail(), dto.getPassword());
        return user;
    }
}
