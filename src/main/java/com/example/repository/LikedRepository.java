
package com.example.repository;

import com.example.model.Article;
import com.example.model.Liked;
import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikedRepository  extends CrudRepository<Liked, Integer> {
    Liked findByArticleAndUser(Article article, User user);
    void updateLikedByArticleAndUser(Article article, User user, boolean liked);
    List<Liked> findByArticle(Article article);
    int countByArticleAndLiked(Article article, boolean liked);
}
