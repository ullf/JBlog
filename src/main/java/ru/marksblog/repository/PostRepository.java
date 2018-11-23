package ru.marksblog.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marksblog.entity.Post;

import java.util.ArrayList;

@Repository
@Transactional
public interface PostRepository extends CrudRepository<Post,Long> {

    @Query("DELETE FROM Post post WHERE post.title=:title")
    @Modifying
    void deletePostByTitle(@Param("title") String title);

    @Query("SELECT post FROM Post post")
    ArrayList<Post> findAllPosts();
}
