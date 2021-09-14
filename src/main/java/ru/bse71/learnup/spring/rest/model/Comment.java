package ru.bse71.learnup.spring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 2:32
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer id;
    private String text;
}
