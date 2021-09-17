package ru.bse71.learnup.spring.rest.reactive.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bse71.learnup.spring.rest.dto.PostDto;
import ru.bse71.learnup.spring.rest.mappers.PostMapper;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.services.PostService;

import java.time.Duration;

/**
 * Created by bse71
 * Date: 15.09.2021
 * Time: 4:37
 */

@Component
public class PostHandler {

    private final PostService postService;
    private final PostMapper postMapper;

    @Autowired
    public PostHandler(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    public Mono<ServerResponse> getAllPosts(ServerRequest request) {

        Flux<PostDto> data = Flux
                .fromIterable(
                        postService.getAllPosts())
                .map(postMapper::toDto);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Post.class);
    }
}
