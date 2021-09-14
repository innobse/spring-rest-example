package ru.bse71.learnup.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bse71.learnup.spring.rest.entities.CommentEntity;

/**
 * Created by bse71
 * Date: 23.08.2021
 * Time: 1:26
 */

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
