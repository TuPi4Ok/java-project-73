package hexlet.code.app.service.User;

import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUser(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User registrateUser(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(long id, User newUser) {
        User oldUser = userRepository.findUserById(id);
        oldUser.setEmail(newUser.getEmail());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(userRepository.findUserById(id));
    }
}
