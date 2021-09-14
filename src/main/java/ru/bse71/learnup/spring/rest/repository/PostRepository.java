package ru.bse71.learnup.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bse71.learnup.spring.rest.entities.PostEntity;

import java.util.List;

/**
 * Created by bse71
 * Date: 23.08.2021
 * Time: 1:26
 */

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> getAllByTitleContains(String titleContains);

    List<PostEntity> getAllByTextNotNull();
}
