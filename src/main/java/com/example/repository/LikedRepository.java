
package com.example.repository;

import com.example.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface LikedRepository  extends CrudRepository<Article, Integer> {
}
