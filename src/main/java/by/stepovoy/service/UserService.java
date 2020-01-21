package by.stepovoy.service;

import by.stepovoy.model.User;
import by.stepovoy.model.UserDTO;

import java.util.List;

public interface UserService {
    User save(UserDTO user);

    void delete(Long id);

    UserDTO update(UserDTO user, Long id);

    User findById(Long id);

    List<User> findAll();
}
