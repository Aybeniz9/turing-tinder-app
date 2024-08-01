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

import java.util.List;
import java.util.Optional;

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
}

