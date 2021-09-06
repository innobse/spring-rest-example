package ru.bse71.learnup.spring.rest.repository.interfaces;

import ru.bse71.learnup.spring.rest.model.Post;

import java.util.Collection;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 2:31
 */

public interface PostRepository {

    Collection<Post> getAllPosts();

    Post getOne(int id);

    boolean save(Post post);

    int getNewId();

    void delete(int id);
}
