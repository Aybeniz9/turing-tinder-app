package az.edu.turing.turingtinderapp.service.impl;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.domain.entity.User;
import az.edu.turing.turingtinderapp.domain.repository.LikeRepository;
import az.edu.turing.turingtinderapp.domain.repository.UserRepository;
import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import az.edu.turing.turingtinderapp.model.dto.UserDto;
import az.edu.turing.turingtinderapp.model.mapper.LikeMapper;
import az.edu.turing.turingtinderapp.model.mapper.UserMapper;
import az.edu.turing.turingtinderapp.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {
    private static final Logger logger = LoggerFactory.getLogger(LikeServiceImpl.class);
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final LikeMapper likeMapper;
    private final UserMapper userMapper;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository,
                           UserRepository userRepository,
                           LikeMapper likeMapper,
                           UserMapper userMapper) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.likeMapper = likeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<LikeDto> getAllLikes() {
        logger.info("Fetching all likes");
        List<Like> likes = likeRepository.findAll();
        return likes.stream()
                .map(likeMapper::toDto)
                .toList();
    }

    @Override
    public Optional<LikeDto> getLikeById(Long id) {
        logger.info("Fetching like with id: {}", id);
        return likeRepository.findById(id)
                .map(likeMapper::toDto);
    }

    @Override
    public void saveLike(LikeDto likeDto) {
        logger.info("Saving like: {}", likeDto);
        Like like = likeMapper.toEntity(likeDto);
        likeRepository.save(like);
        logger.info("Like saved successfully");
    }

    @Override
    public void deleteLikeById(Long id) {
        logger.info("Deleting like with id: {}", id);
        likeRepository.deleteById(id);
        logger.info("Like deleted successfully");
    }

    @Override
    public void updateLike(Long id, LikeDto likeDto) {
        logger.info("Updating like with id: {}", id);
        if (likeRepository.existsById(id)) {
            Like like = likeMapper.toEntity(likeDto);
            like.setId(id);
            likeRepository.save(like);
            logger.info("Like updated successfully");
        } else {
            logger.error("Like not found with id: {}", id);
            throw new RuntimeException("Like not found with id: " + id);
        }
    }

    @Override
    public List<LikeDto> getLikesByUserId(Long userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes.stream()
                .map(likeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getLikedUsersByUserId(Long userId) {
        logger.info("Fetching liked users for user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Set<Long> likedUserIds = user.getLikedUserIds();
        List<User> likedUsers = userRepository.findAllById(likedUserIds);

        return likedUsers.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
//    @Override
//    public List<UserDto> getLikedUsersByUserId(Long userId) {
//        List<Like> likes = likeRepository.findByUserId(userId);
//        List<Long> likedUserIds = likes.stream()
//                .map(Like::getLikedUserId)
//                .collect(Collectors.toList());
//
//        List<User> likedUsers = userRepository.findAllById(likedUserIds);
//        return likedUsers.stream()
//                .map(userMapper::toDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public Optional<LikeDto> getLikeByUserIds(Long userId, Long likedUserId) {
        Optional<Like> like = likeRepository.findByUserIdAndLikedUserId(userId, likedUserId);
        return like.map(likeMapper::toDto);
    }

    @Override
    public List<LikeDto> getLikesByReaction(Boolean reaction) {
        List<Like> likes = likeRepository.findByReaction(reaction);
        return likes.stream()
                .map(likeMapper::toDto)
                .collect(Collectors.toList());
    }

}

