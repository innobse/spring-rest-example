package ru.bse71.learnup.spring.rest.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.bse71.learnup.spring.rest.model.Post;

import java.io.Serializable;

/**
 * Created by bse71
 * Date: 14.09.2021
 * Time: 1:22
 */

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        final String username = authentication.getName();
        if (targetDomainObject instanceof Post) {
            final Post targetPost = (Post) targetDomainObject;
            return targetPost.getAuthor().equals(username);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
