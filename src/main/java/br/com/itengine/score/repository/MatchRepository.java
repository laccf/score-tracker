package br.com.itengine.score.repository;

import br.com.itengine.score.entity.Match;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MyLittle on 19/05/16.
 */
public interface MatchRepository extends CrudRepository<Match, Integer> {
    List<Match> findAll();
    List<Match> findByIsPlayedTrue();
    Match findById(Integer id);

}
