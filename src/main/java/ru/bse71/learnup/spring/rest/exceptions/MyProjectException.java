package ru.bse71.learnup.spring.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description
 *
 * @author bse71
 * Created on 05.09.2021
 * @since
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyProjectException extends RuntimeException {

    public MyProjectException(String message) {
        super(message);
    }
}
