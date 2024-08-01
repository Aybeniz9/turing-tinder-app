
package az.edu.turing.turingtinderapp.service;

import az.edu.turing.turingtinderapp.domain.entity.User;
import az.edu.turing.turingtinderapp.model.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(Long id);

    UserDto saveUser(UserDto userDto);


    void deleteUserById(Long id);

    UserDto updateUser(UserDto userDto);
    List<UserDto> getUsersByName(String name);

    List<UserDto> getUsersByPhotoUrl(String photoUrl);

    List<UserDto> getUsersByLastLogin(LocalDate lastLogin);

    List<UserDto> getLikedUsersByUserId(Long userId);

    void updateUserLikes(Long userId, Set<Long> likedUserIds);

}