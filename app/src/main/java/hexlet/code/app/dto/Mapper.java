package hexlet.code.app.dto;

import hexlet.code.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCreatedAt());
    }
}
