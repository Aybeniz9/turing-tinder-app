package az.edu.turing.turingtinderapp.service;

import az.edu.turing.turingtinderapp.domain.entity.User;
import az.edu.turing.turingtinderapp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


        private final UserRepository userRepository;

        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public List<User> getAllUsers() {
            return userRepository.getAll();
        }

        public Optional<User> getUserById(Long id) {
            return userRepository.get(id);
        }

        public User saveUser(User user) {
            userRepository.insert(user);
            return user;//duzelis edilecek save metodu yazilaraq
        }

        public void deleteUser(Long id) {
            userRepository.delete(id);
        }

        public List<User> getLikedUsers(Long userId) {
            Optional<User> userOptional = userRepository.get(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Set<Long> likedUserIds = user.getLikedUserIds();
                return userRepository.findAllByIds(likedUserIds);
            }
            return List.of();
        }

        public void likeUser(Long userId, Long likedUserId) {
            Optional<User> userOptional = userRepository.get(userId);
            Optional<User> likedUserOptional = userRepository.get(likedUserId);

            if (userOptional.isPresent() && likedUserOptional.isPresent()) {
                User user = userOptional.get();
                user.getLikedUserIds().add(likedUserId);
                userRepository.insert(user);
            }
        }

        public void unlikeUser(Long userId, Long likedUserId) {
            Optional<User> userOptional = userRepository.get(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.getLikedUserIds().remove(likedUserId);
                userRepository.insert(user);//save deyisikliyi edilecek
            }
        }
    }


//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.getAll();
//    }
//
//    public Optional<User> getUserById(Long id) {
//        return userRepository.get(id);
//    }
//
//    public User saveUser(User user) {
//        if (user.getId() == null) {
//            userRepository.insert(user);
//        } else {
//            userRepository.update(user);
//        }
//        return user;
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.delete(id);
//    }
//



