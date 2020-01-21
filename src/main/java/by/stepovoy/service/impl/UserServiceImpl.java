package by.stepovoy.service.impl;

import by.stepovoy.dao.UserDao;
import by.stepovoy.exception.UserNotFoundException;
import by.stepovoy.model.User;
import by.stepovoy.model.UserDTO;
import by.stepovoy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }


    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO);
        return dao.save(user);
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
            BeanUtils.copyProperties(userDTO, user, "password", "username");
            dao.save(user);
        }
        return userDTO;
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id).orElseThrow(() -> new UserNotFoundException("Not found user with such id = " + id));
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }
}
