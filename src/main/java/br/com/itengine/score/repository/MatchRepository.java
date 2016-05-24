package br.com.itengine.score.repository;

import br.com.itengine.score.entity.Match;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MyLittle on 19/05/16.
 */
public interface MatchRepository extends CrudRepository<Match, Integer> {
}
