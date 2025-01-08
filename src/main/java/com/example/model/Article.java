package com.example.model;

import com.example.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.DateFormat;
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

    public String toString() {
        String newDate = DateFormat.getDateTimeInstance().format(this.date);
        return ", date=" + newDate + ", content=" + content + "]";
    }
}
