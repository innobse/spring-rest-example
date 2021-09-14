package ru.bse71.learnup.spring.rest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by bse71
 * Date: 31.08.2021
 * Time: 2:39
 */

@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTextEntity extends AbstractEntity {

    @Column
    @Getter
    @Setter
    protected String text;

    public AbstractTextEntity(Integer id, String text) {
        super(id);
        this.text = text;
    }
}
