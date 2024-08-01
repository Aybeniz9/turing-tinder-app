package az.edu.turing.turingtinderapp.service.impl;

import az.edu.turing.turingtinderapp.domain.entity.User;
import az.edu.turing.turingtinderapp.domain.repository.UserRepository;
import az.edu.turing.turingtinderapp.model.dto.UserDto;
import az.edu.turing.turingtinderapp.model.mapper.UserMapper;
import az.edu.turing.turingtinderapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        logger.info("Saving user: {}", userDto);
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }


    @Override
    public void deleteUserById(Long id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        logger.info("Updating user: {}", userDto);
        if (userRepository.existsById(userDto.id())) {
            User user = userMapper.toEntity(userDto);
            User updatedUser = userRepository.save(user);
            return userMapper.toDto(updatedUser);
        } else {
            logger.warn("User with ID {} does not exist", userDto.id());
            return null;
        }
    }
    @Override
    public List<UserDto> getUsersByName(String name) {
        logger.info("Fetching users with name: {}", name);
        List<User> users = userRepository.findByNameContaining(name);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByPhotoUrl(String photoUrl) {
        logger.info("Fetching users with photo URL: {}", photoUrl);
        List<User> users = userRepository.findByPhotoUrl(photoUrl);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByLastLogin(LocalDate lastLogin) {
        logger.info("Fetching users with last login date: {}", lastLogin);
        List<User> users = userRepository.findByLastLogin(lastLogin);
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getLikedUsersByUserId(Long userId) {
        logger.info("Fetching liked users for user with id: {}", userId);
        List<Long> likedUserIds = userRepository.findLikedUserIdsByUserId(userId);
        List<User> likedUsers = userRepository.findAllById(likedUserIds);
        return likedUsers.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserLikes(Long userId, Set<Long> likedUserIds) {
        logger.info("Updating likes for user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setLikedUserIds(likedUserIds);
        userRepository.save(user);
        logger.info("User likes updated successfully");
    }

}

