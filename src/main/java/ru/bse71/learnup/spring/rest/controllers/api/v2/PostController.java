package ru.bse71.learnup.spring.rest.controllers.api.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bse71.learnup.spring.rest.dto.PostDto;
import ru.bse71.learnup.spring.rest.mappers.PostMapper;
import ru.bse71.learnup.spring.rest.model.Post;
import ru.bse71.learnup.spring.rest.services.SecuredPostService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 1:21
 */

@RestController("postControllerSecured")
@RequestMapping("/api/v2/post")
public class PostController {

    private SecuredPostService service;
    private PostMapper mapper;

    @Autowired
    public PostController(SecuredPostService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(
            value = "/anno/by-user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getIfUserAnnotation(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getIfUserAnnotation(id));
    }

    @GetMapping(
            value = "/secured/by-user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getSecuredByUser(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getSecuredByUser(id));
    }

    @GetMapping(
            value = "/secured/by-admin/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getSecuredByAdmin(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getSecuredByAdmin(id));
    }

    @GetMapping(
            value = "/roles-allowed/by-user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getRolesAllowedByUser(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getRolesAllowedByUser(id));
    }

    @GetMapping(
            value = "/roles-allowed/by-admin/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getRolesAllowedByAdmin(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getRolesAllowedByAdmin(id));
    }

    @GetMapping(
            value = "/pre-auth/by-user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getPreAuthorizeByUser(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getPreAuthorizeByUser(id));
    }

    @GetMapping(
            value = "/pre-auth/by-admin/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getPreAuthorizeByAdmin(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getPreAuthorizeByAdmin(id));
    }

    @GetMapping(
            value = "/post-auth/by-user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getPostAuthorizeByUser(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getPostAuthorizeByUser(id));
    }

    @GetMapping(
            value = "/post-auth/by-admin/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDto getPostAuthorizeByAdmin(@PathVariable("id") int id) {
        return mapper.toDto(
                service.getPostAuthorizeByAdmin(id));
    }

    //////////////////////////////////////////////////////////////////////////////////

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
