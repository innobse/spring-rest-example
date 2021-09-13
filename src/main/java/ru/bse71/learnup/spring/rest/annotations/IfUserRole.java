package ru.bse71.learnup.spring.rest.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bse71
 * Date: 14.09.2021
 * Time: 2:07
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_USER')")
public @interface IfUserRole {
}
