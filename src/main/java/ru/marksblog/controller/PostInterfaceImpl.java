package ru.marksblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.Post;
import ru.marksblog.service.PostService;

import java.util.Iterator;

@Service
public class PostInterfaceImpl implements PostInterface {

    @Autowired
    private PostService postService;

    @Override
    public void addPost(Post post) {
        postService.persist(post);
    }

    @Override
    public void deletePostByTitle(String title) {
        postService.deletePostByTitle(title);
    }

    @Override
    public Iterator<Post> findAll() {
        return postService.findAll();
    }
}
