package ru.bse71.learnup.spring.rest.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/v1/post")
public class PostController {

    private PostService service;
    private PostMapper mapper;

    @Autowired
    public PostController(PostService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PostDto> getAllPosts() {
        final Collection<Post> allPosts = service.getAllPosts();
        final List<PostDto> result = new ArrayList<>(allPosts.size());
        for (Post post : allPosts) {
            result.add(mapper.toDto(post));
        }
        return result;
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getPost(@PathVariable("id") int id) {
        return mapper.toDto(
                service.get(id));
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDto addPost(@RequestBody PostDto postDto) {
        return mapper.toDto(
                service.add(
                        mapper.toModel(postDto)));
    }

    @PutMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDto updatePost(@PathVariable int id, @RequestBody PostDto postDto) {
        postDto.setId(id);
        return mapper.toDto(
                service.update(
                        mapper.toModel(postDto)));
    }

    @DeleteMapping(
            value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}
