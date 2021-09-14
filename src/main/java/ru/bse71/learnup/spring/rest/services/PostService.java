package ru.bse71.learnup.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.bse71.learnup.spring.rest.entities.PostEntity;
import ru.bse71.learnup.spring.rest.mappers.PostMapper;
import ru.bse71.learnup.spring.rest.model.Comment;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.repository.PostRepository;

import java.util.ArrayList;
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
    private PostMapper mapper;

    @Autowired
    public PostService(PostRepository repository, PostMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostFilter("hasPermission(filterObject, 'READ')")
    public Collection<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        for (PostEntity entity: repository.findAll()) {
            posts.add(mapper.toModel(entity));
        }
        return posts;
    }

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public Post get(int id) {
        return mapper.toModel(
                repository.getById(id));
    }

    public Post add(Post post) {
        repository.save(
                mapper.toEntity(post));
        return post;
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Comment> getAllCommentsByPostId(int postId) {
        final PostEntity entity = repository.getById(postId);
        final Post post = mapper.toModel(entity);
        return post.getComments();
    }

    public Post update(Post post) {
        repository.save(
                mapper.toEntity(post));
        return mapper.toModel(
                repository.getById(post.getId()));
    }
}
