package com.example.model;

import jakarta.persistence.*;

@Entity
public class Liked {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // 1 si like, 0 si dislike
    private Boolean liked;

    public Article getArticle() {
        return article;
    }

    public User getUser() {
        return user;
    }

    public Boolean getLike() {
        return liked;
    }

    public void setLike(Boolean liked) {
        this.liked = liked;
    }
}