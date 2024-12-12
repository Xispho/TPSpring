package com.example.repository;

import com.example.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository  extends CrudRepository<Article, Integer> {
}
