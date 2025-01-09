package com.example.controller;

import com.example.model.Liked;
import com.example.model.Article;
import com.example.model.User;
import com.example.repository.ArticleRepository;
import com.example.repository.LikedRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path="/liked")
public class LikedController {

    @Autowired
    private LikedRepository likedRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/like")
    public @ResponseBody String addLiked (@RequestParam int articleId, @RequestParam int userId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Liked existingLiked = likedRepository.findByArticleAndUser(article, user);
        if (existingLiked != null) {
            if (!existingLiked.getLiked()) {
                likedRepository.updateLikedByArticleAndUser(article, user, true);
                return "Update like status to like";
            }
            likedRepository.delete(existingLiked);
            return "Liked cancelled";
        } else {
            likedRepository.save(new Liked(article, user, true));
            return "Liked added";
        }
    }

    @PostMapping(path="/dislike")
    public @ResponseBody String addDisliked (@RequestParam int articleId, @RequestParam int userId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Liked existingLiked = likedRepository.findByArticleAndUser(article, user);
        if (existingLiked != null) {
            if (existingLiked.getLiked()) {
                likedRepository.updateLikedByArticleAndUser(article, user, false);
                return "Update like status to dislike";
            }
            likedRepository.delete(existingLiked);
            return "Disliked cancelled";
        } else {
            likedRepository.save(new Liked(article, user, false));
            return "Disliked added";
        }
    }

    @GetMapping(path="/{articleId}/users")
    public @ResponseBody Iterable<Map<String, String>> getUsersByArticle (@PathVariable int articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));

        return likedRepository.findByArticle(article).stream().map(liked -> {
            User user = liked.getUser();
            String userLiked = liked.getLiked() ? "like" : "dislike";
            return Map.of("userName", user.getName(), "liked", userLiked);
        })::iterator;
    }

    @GetMapping(path="/{articleId}/likes")
    public @ResponseBody int getLikeCountByArticle (@PathVariable int articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));

        return likedRepository.countByArticleAndLiked(article, true);
    }

    @GetMapping(path="/{articleId}/dislikes")
    public @ResponseBody int getDislikeCountByArticle (@PathVariable int articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));

        return likedRepository.countByArticleAndLiked(article, false);
    }

}