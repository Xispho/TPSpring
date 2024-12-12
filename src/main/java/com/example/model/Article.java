package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "articleId")
    private Integer id;

    @Getter
    @Setter
    private int authorId;

    @Getter
    @Setter
    private Timestamp date;

    @Getter
    @Setter
    private String content;
}
