package hexlet.code.app.service.User;

import hexlet.code.app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public User registrateUser(User user);
    public User getUser(long id);
    public List<User> getUsers();
    public User updateUser(long id, User newUser);
    public void deleteUser(long id);
}
