package az.edu.turing.turingtinderapp.controller;

import az.edu.turing.turingtinderapp.model.dto.LikeDto;
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
}
//package az.edu.turing.turingtinderapp.controller;
//
//import az.edu.turing.turingtinderapp.domain.entity.Like;
//import az.edu.turing.turingtinderapp.service.LikeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/likes")
//public class LikeController {
//
//    private final LikeService likeService;
//
//    @Autowired
//    public LikeController(LikeService likeService) {
//        this.likeService = likeService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Like> createLike(@RequestBody Like like) {
//        Like savedLike = likeService.insert(like);
//        return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
//    }
//
//    // Get all likes
//    @GetMapping
//    public ResponseEntity<List<Like>> getAllLikes() {
//        List<Like> likes = likeService.getAllLikes();
//        return new ResponseEntity<>(likes, HttpStatus.OK);
//    }
//
//    // Get a like by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Like> getLikeById(@PathVariable Long id) {
//        Optional<Like> like = likeService.getLikeById(id);
//        if (like.isPresent()) {
//            return new ResponseEntity<>(like.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete a like by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLikeById(@PathVariable Long id) {
//        Optional<Like> like = likeService.getLikeById(id);
//        if (like.isPresent()) {
//            likeService.deleteLikeById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Like> updateLike(@PathVariable Long id, @RequestBody Like like) {
//        try {
//            Like updatedLike = likeService.updateLike(id, like);
//            return new ResponseEntity<>(updatedLike, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
//

//@Controller
//@RequestMapping("/likes")
//public class LikeController {
//    private final LikeService likeService;
//
//    @Autowired
//    public LikeController(LikeService likeService) {
//        this.likeService = likeService;
//    }
//
//    @GetMapping
//    public String getAllLikes(Model model) {
//        List<Like> likes = likeService.getAllLikes();
//        model.addAttribute("likes", likes);
//        return "likeList"; // Name of the view
//    }
//
//    @GetMapping("/{id}")
//    public String getLikeById(@PathVariable Long id, Model model) {
//        Optional<Like> like = likeService.getLikeById(id);
//        like.ifPresent(value -> model.addAttribute("like", value));
//        return "likeDetail"; // Name of the view
//    }
//
//    @PostMapping
//    public String saveLike(@ModelAttribute Like like, Model model) {
//        likeService.saveLike(like);
//        model.addAttribute("like", like);
//        return "likeSaved"; // Name of the view
//    }
//
//    @PutMapping("/{id}")
//    public String updateLike(@PathVariable Long id, @ModelAttribute Like like, Model model) {
//        like.setId(id);
//        likeService.update(like);
//        model.addAttribute("like", like);
//        return "likeUpdated"; // Name of the view
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteLike(@PathVariable Long id, Model model) {
//        likeService.deleteLikeById(id);
//        return "likeDeleted"; // Name of the view
//    }
//}
