package ru.bse71.learnup.spring.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import ru.bse71.learnup.spring.rest.reactive.handlers.PostHandler;

/**
 * Created by bse71
 * Date: 15.09.2021
 * Time: 4:30
 */

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(PostHandler greetingHandler) {
        RequestPredicate route = RequestPredicates
                .GET("/api/router/reactor/post")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions
                .route(route, greetingHandler::getAllPosts);
    }
}
