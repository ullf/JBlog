package ru.marksblog.controller;

import org.springframework.stereotype.Repository;
import ru.marksblog.entity.Post;
import java.util.Iterator;

@Repository
public interface PostInterface {

    void addPost(Post post);
    void deletePostByTitle(String title);
    Iterator<Post> findAll();
}
