package ru.marksblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marksblog.entity.Post;
import ru.marksblog.repository.PostRepository;

import java.util.Iterator;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void persist(Post post){
        postRepository.save(post);
    }

    public void deletePostByTitle(String title){
        postRepository.deletePostByTitle(title);
    }

    public Iterator<Post> findAll(){
       return postRepository.findAll().iterator();
    }
}
