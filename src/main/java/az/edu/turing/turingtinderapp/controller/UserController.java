package az.edu.turing.turingtinderapp.controller;

import az.edu.turing.turingtinderapp.domain.entity.User;
import az.edu.turing.turingtinderapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/choice")
    public ResponseEntity<Void> handleUserChoice(@RequestParam Long userId, @RequestParam String choice) {
        // Handle the user's choice (e.g., save to database or session)
        // Redirect or forward to the next profile
        return ResponseEntity.ok().build(); // Change as necessary
    }

//    @GetMapping("/liked")
//    public ResponseEntity<List<User>> getLikedUsers() {
//        // Retrieve and return the list of liked users
//        List<User> likedUsers = userService.getLikedUsers(); // Assume this method is implemented
//        return ResponseEntity.ok(likedUsers);
//    }
}
