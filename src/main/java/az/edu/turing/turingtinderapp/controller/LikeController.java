package az.edu.turing.turingtinderapp.controller;


import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.service.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public String getAllLikes(Model model) {
        List<Like> likes = likeService.getAll();
        model.addAttribute("likes", likes);
        return "likeList"; // Name of the view
    }

    @GetMapping("/{id}")
    public String getLikeById(@PathVariable Long id, Model model) {
        Optional<Like> like = likeService.get(id);
        like.ifPresent(value -> model.addAttribute("like", value));
        return "likeDetail"; // Name of the view
    }

    @PostMapping
    public String saveLike(@ModelAttribute Like like, Model model) {
        likeService.insert(like);
        model.addAttribute("like", like);
        return "likeSaved"; // Name of the view
    }

    @PutMapping("/{id}")
    public String updateLike(@PathVariable Long id, @ModelAttribute Like like, Model model) {
        like.setId(id);
        likeService.update(like);
        model.addAttribute("like", like);
        return "likeUpdated"; // Name of the view
    }

    @DeleteMapping("/{id}")
    public String deleteLike(@PathVariable Long id, Model model) {
        likeService.delete(id);
        return "likeDeleted"; // Name of the view
    }
}
