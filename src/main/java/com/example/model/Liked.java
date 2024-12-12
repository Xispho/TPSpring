package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Liked {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Getter
    @OneToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @Getter
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    // 1 si like, 0 si dislike
    @Getter
    @Setter
    private Boolean liked;

}