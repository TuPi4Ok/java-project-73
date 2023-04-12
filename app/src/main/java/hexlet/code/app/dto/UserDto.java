package hexlet.code.app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UserDto {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date createdAt;

    public UserDto(long id, String email, String firstName, String lastName, Date createdAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }
}
