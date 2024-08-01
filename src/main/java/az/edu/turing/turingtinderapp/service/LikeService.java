package az.edu.turing.turingtinderapp.service;

import java.util.List;
import java.util.Optional;

import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import az.edu.turing.turingtinderapp.model.dto.UserDto;


public interface LikeService {

    List<LikeDto> getAllLikes();

    Optional<LikeDto> getLikeById(Long id);

    void saveLike(LikeDto likeDto);

    void deleteLikeById(Long id);

    void updateLike(Long id, LikeDto likeDto);
    List<LikeDto> getLikesByUserId(Long userId);

    List<UserDto> getLikedUsersByUserId(Long userId);

    Optional<LikeDto> getLikeByUserIds(Long userId, Long likedUserId);

    List<LikeDto> getLikesByReaction(Boolean reaction);
}

