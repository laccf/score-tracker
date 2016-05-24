package br.com.itengine.score.repository;


import br.com.itengine.score.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MyLittle on 17/05/16.
 */
public interface TeamRepository extends CrudRepository<Team, Integer> {

    List<Team> findByName(String name);
    List<Team> findByNameIgnoreCase(String name);

}
