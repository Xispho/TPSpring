
package com.example.repository;

import com.example.model.Article;
import com.example.model.Liked;
import com.example.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikedRepository  extends CrudRepository<Liked, Integer> {
    Liked findByArticleAndUser(Article article, User user);

    @Modifying
    @Transactional
    @Query("UPDATE Liked l SET l.liked = :liked WHERE l.article = :article AND l.user = :user")
    void updateLikedByArticleAndUser(@Param("article") Article article, @Param("user") User user, @Param("liked") boolean liked);

    List<Liked> findByArticle(Article article);

    int countByArticleAndLiked(Article article, boolean liked);
}
