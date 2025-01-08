package com.example.controller;

import com.example.repository.ArticleRepository;
import com.example.model.Article;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
@RequestMapping(path="/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="")
    public @ResponseBody String addArticle (@RequestBody Article article) {
        article.setDate(Timestamp.from(Instant.now()));
        articleRepository.save(article);
        String author = userRepository.findById(article.getAuthorId()).get().getName();
        return "Article created : Article [author="+ author + article.toString();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteArticle (@PathVariable int id) {
        articleRepository.deleteById(id);
        return "Article deleted";
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Article getArticle (@PathVariable int id) {
        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
