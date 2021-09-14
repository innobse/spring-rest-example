package ru.bse71.learnup.spring.rest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by bse71
 * Date: 18.08.2021
 * Time: 23:42
 */


@Entity
@Table(name = "comments")
@NoArgsConstructor
public class CommentEntity extends AbstractTextEntity {

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private PostEntity post;

    public CommentEntity(Integer id, String text, PostEntity post) {
        super(id, text);
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
