package ru.bse71.learnup.spring.rest.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.bse71.learnup.spring.rest.model.Comment;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.repository.interfaces.PostRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Arrays.asList;
import static ru.bse71.learnup.spring.rest.util.PostUtils.generateCommentText;
import static ru.bse71.learnup.spring.rest.util.PostUtils.generateText;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 2:30
 */

@Repository
@Primary
@Profile("in-memory")
public class InMemoryPostRepository implements PostRepository {

    private final Map<Integer, Post> posts = new HashMap<>();
    private static final Random RAND = new Random();

    @PostConstruct
    public void init() {
        posts.put(
                1,
                new Post(1, "Пост 1", generateText(), asList(
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText())
                )));

        posts.put(
                2,
                new Post(2, "Пост 2", generateText(), asList(
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText())
                )));

        posts.put(
                3,
                new Post(3, "Пост 3", generateText(), asList(
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText()),
                        new Comment(generateCommentText())
                )));
    }

    @Override
    public Collection<Post> getAllPosts() {
        return posts.values();
    }

    @Override
    public Post getOne(int id) {
        return posts.get(id);
    }

    @Override
    public boolean save(Post post) {
        posts.put(post.getId(), post);
        return true;
    }

    @Override
    public int getNewId() {
        return RAND.nextInt();
    }

    @Override
    public void delete(int id) {
        posts.remove(id);
    }
}
