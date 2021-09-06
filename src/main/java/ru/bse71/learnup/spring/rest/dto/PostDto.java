package ru.bse71.learnup.spring.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bse71.learnup.spring.rest.model.Comment;

import java.util.List;

/**
 * Created by bse71
 * Date: 06.09.2021
 * Time: 11:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @JsonProperty
    private Integer id;

    //  Нарочно сделано, чтобы показать, что DTO может отличаться
    @JsonProperty("name")
    private String title;

    @JsonProperty
    private String text;

    @JsonProperty
    private List<Comment> comments;
}
