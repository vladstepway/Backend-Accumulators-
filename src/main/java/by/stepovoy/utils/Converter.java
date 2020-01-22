package by.stepovoy.utils;

import by.stepovoy.model.User;
import by.stepovoy.model.UserDTO;
import org.springframework.beans.BeanUtils;

public class Converter {
    public static User convertToEntity(UserDTO source, String... properties) {
        User target = new User();
        BeanUtils.copyProperties(source, target, properties);
        return target;
    }

    public static UserDTO convertToDTO(User user, String... properties) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO, properties);
        return userDTO;
    }

}
