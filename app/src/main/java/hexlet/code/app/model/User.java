package hexlet.code.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Getter
@Setter
@Table(name = "my_users")
@Valid
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Email
    String email;
    @Size(min = 3)
//    @JsonIgnore
    String password;
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    Date createdAt;
}
