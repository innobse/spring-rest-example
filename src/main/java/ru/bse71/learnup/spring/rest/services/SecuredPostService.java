package ru.bse71.learnup.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import ru.bse71.learnup.spring.rest.annotations.IfUserRole;
import ru.bse71.learnup.spring.rest.model.Comment;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.repository.interfaces.PostRepository;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 2:29
 */

@Service
public class SecuredPostService {

    private PostRepository repository;

    @Autowired
    public SecuredPostService(PostRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("isAuthenticated()")
    @PostFilter("hasPermission(filterObject, 'get')")
//    @PostFilter("filterObject.author.equals(principal.username)")
    public Collection<Post> getAllPosts() {
        return repository.getAllPosts();
    }

    @PreAuthorize("hasRole(\"ADMIN\")")
    public Post getPreAuthorizeByAdmin(int id) {
        return repository.getOne(id);
    }

    @PostFilter("filterObject.author.equals(principal.username)")
    @PreAuthorize("hasRole(\"USER\")")
    public Post getPreAuthorizeByUser(int id) {
        return repository.getOne(id);
    }

    @IfUserRole
    public Post getIfUserAnnotation(int id) {
        return repository.getOne(id);
    }

    @PostAuthorize("hasRole(\"ADMIN\")")
    public Post getPostAuthorizeByAdmin(int id) {
        return repository.getOne(id);
    }

    @PostAuthorize("hasRole(\"USER\")")
    public Post getPostAuthorizeByUser(int id) {
        return repository.getOne(id);
    }

    @Secured("ROLE_USER")
    public Post getSecuredByUser(int id) {
        return repository.getOne(id);
    }

    @Secured("ROLE_ADMIN")
    public Post getSecuredByAdmin(int id) {
        return repository.getOne(id);
    }

    @RolesAllowed("ROLE_USER")
    public Post getRolesAllowedByUser(int id) {
        return repository.getOne(id);
    }

    @RolesAllowed("ROLE_ADMIN")
    public Post getRolesAllowedByAdmin(int id) {
        return repository.getOne(id);
    }

    public Post add(Post post) {
        post.setId(repository.getNewId());
        repository.save(post);
        return post;
    }

//    @PreAuthorize("hasRole(\"USER\")")
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
//    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})


    @PreAuthorize("hasRole(\"USER\")")
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
