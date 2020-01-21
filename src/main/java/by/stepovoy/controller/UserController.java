package by.stepovoy.controller;

import by.stepovoy.model.ApiResponse;
import by.stepovoy.model.User;
import by.stepovoy.model.UserDTO;
import by.stepovoy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserDTO> saveUser(@RequestBody UserDTO user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully", userService.save(user));
    }

    @GetMapping
    public ApiResponse<List<UserDTO>> listUser() {
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully", userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUser(@PathVariable Long id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully", userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDTO> update(@RequestBody UserDTO user,@PathVariable Long id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully", userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully", new UserDTO());
    }

}
