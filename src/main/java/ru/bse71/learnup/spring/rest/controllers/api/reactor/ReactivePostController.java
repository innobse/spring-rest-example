package ru.bse71.learnup.spring.rest.controllers.api.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.bse71.learnup.spring.rest.dto.PostDto;
import ru.bse71.learnup.spring.rest.mappers.PostMapper;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.services.PostService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 1:21
 */

@RestController
@RequestMapping("/api/reactor/post")
public class ReactivePostController {

    private PostService service;
    private PostMapper mapper;

    @Autowired
    public ReactivePostController(PostService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PostDto> getAllPosts() {
        return Flux.fromIterable(service.getAllPosts())
                .map(mapper::toDto);
    }
}
