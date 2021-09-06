package ru.bse71.learnup.spring.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
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
public class CommentDto extends RepresentationModel<CommentDto> {

    @JsonProperty
    private String text;
}
