package by.stepovoy.service;

import by.stepovoy.model.User;
import by.stepovoy.model.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO save(UserDTO user);

    void delete(Long id);

    UserDTO update(UserDTO user, Long id);

    User findById(Long id);

    Page<UserDTO> findAll(Pageable pageable);
//    Page<User> findAll(Pageable pageable);
}
