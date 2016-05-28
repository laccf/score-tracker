package br.com.itengine.score.repository;

import br.com.itengine.score.entity.League;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MyLittle on 19/05/16.
 */
public interface LeagueRepository extends CrudRepository<League,Integer> {

    List<League> findAll();
    League findById(Integer id);
}
