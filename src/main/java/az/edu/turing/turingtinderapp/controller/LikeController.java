package az.edu.turing.turingtinderapp.controller;

import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import az.edu.turing.turingtinderapp.model.dto.UserDto;
import az.edu.turing.turingtinderapp.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private static final Logger logger = LoggerFactory.getLogger(LikeController.class);
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<List<LikeDto>> getAllLikes() {
        logger.info("Fetching all likes");
        List<LikeDto> likes = likeService.getAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikeDto> getLikeById(@PathVariable("id") Long id) {
        logger.info("Fetching like with id: {}", id);
        Optional<LikeDto> likeDto = likeService.getLikeById(id);
        return likeDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeDto likeDto) {
        logger.info("Creating new like: {}", likeDto);
        likeService.saveLike(likeDto);
        return new ResponseEntity<>(likeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LikeDto> updateLike(@PathVariable("id") Long id, @RequestBody LikeDto likeDto) {
        logger.info("Updating like with id: {} with data: {}", id, likeDto);
        try {
            likeService.updateLike(id, likeDto);
            return new ResponseEntity<>(likeDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error updating like with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable("id") Long id) {
        logger.info("Deleting like with id: {}", id);
        likeService.deleteLikeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeDto>> getLikesByUserId(@PathVariable Long userId) {
        List<LikeDto> likes = likeService.getLikesByUserId(userId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/user/{userId}/liked")
    public ResponseEntity<List<UserDto>> getLikedUsersByUserId(@PathVariable Long userId) {
        List<UserDto> likedUsers = likeService.getLikedUsersByUserId(userId);
        return ResponseEntity.ok(likedUsers);
    }

    @GetMapping("/user/{userId}/liked/{likedUserId}")
    public ResponseEntity<LikeDto> getLikeByUserIds(@PathVariable Long userId, @PathVariable Long likedUserId) {
        Optional<LikeDto> likeDto = likeService.getLikeByUserIds(userId, likedUserId);
        return likeDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/reaction/{reaction}")
    public ResponseEntity<List<LikeDto>> getLikesByReaction(@PathVariable Boolean reaction) {
        List<LikeDto> likes = likeService.getLikesByReaction(reaction);
        return ResponseEntity.ok(likes);
    }
}