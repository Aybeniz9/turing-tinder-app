package az.edu.turing.turingtinderapp.controller;

import az.edu.turing.turingtinderapp.model.dto.UserDto;
import az.edu.turing.turingtinderapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Request received to get all users");
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        logger.info("Request received to get user with ID: {}", id);
        Optional<UserDto> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        logger.info("Request received to create user: {}", userDto);

        if (userDto == null) {
            logger.error("UserDto is null");
            return ResponseEntity.badRequest().build();
        }

        try {
            UserDto createdUser = userService.saveUser(userDto);
            if (createdUser != null) {
                return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
            } else {
                logger.error("Failed to create user");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while creating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        logger.info("Request received to update user with ID: {}", id);
        userDto = new UserDto(id, userDto.name(), userDto.password(), userDto.photoUrl(), userDto.lastLogin(), userDto.likedUserIds());
        UserDto updatedUser = userService.updateUser(userDto);
        return updatedUser != null ? ResponseEntity.ok(updatedUser)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        logger.info("Request received to delete user with ID: {}", id);
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}


//    @GetMapping("/liked")
//    public ResponseEntity<List<User>> getLikedUsers() {
//        // Retrieve and return the list of liked users
//        List<User> likedUsers = userService.getLikedUsers(); // Assume this method is implemented
//        return ResponseEntity.ok(likedUsers);
//    }

