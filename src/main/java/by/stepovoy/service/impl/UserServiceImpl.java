package by.stepovoy.service.impl;

import by.stepovoy.dao.UserDao;
import by.stepovoy.exception.UserNotFoundException;
import by.stepovoy.model.User;
import by.stepovoy.model.UserDTO;
import by.stepovoy.service.UserService;
import by.stepovoy.utils.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }


    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = new User(userDTO);
        return Converter.convertToDTO(dao.save(user), "password");
    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            dao.deleteById(id);
        }
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        User user = findById(id);
        if (user != null) {
            User userToUpdate = Converter.convertToEntity(userDTO, "password", "username");
            dao.save(userToUpdate);
        }
        return userDTO;
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id).orElseThrow(() -> new UserNotFoundException("Not found user with such id = " + id));
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> usersPage = dao.findAll(pageable);
        return new PageImpl<>(
                usersPage.getContent().stream()
                        .map(user -> Converter.convertToDTO(user, "password", "username"))
                        .collect(Collectors.toList()), pageable, usersPage.getTotalElements());

    }

}
