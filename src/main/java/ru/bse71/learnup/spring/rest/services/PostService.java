package ru.bse71.learnup.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bse71.learnup.spring.rest.dto.PostDto;
import ru.bse71.learnup.spring.rest.model.Comment;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.repository.interfaces.PostRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 2:29
 */

@Service
public class PostService {

    private PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Collection<Post> getAllPosts() {
        return repository.getAllPosts();
    }

    public Post get(int id) {
        return repository.getOne(id);
    }

    public Post add(Post post) {
        post.setId(repository.getNewId());
        repository.save(post);
        return post;
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public List<Comment> getAllCommentsByPostId(int postId) {
        final Post post = repository.getOne(postId);
        return post == null ? null : post.getComments();
    }

    public Post update(Post post) {
        repository.save(post);
        return repository.getOne(post.getId());
    }
}
