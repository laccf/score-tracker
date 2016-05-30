package br.com.itengine.score.repository;

import br.com.itengine.score.entity.League;
import br.com.itengine.score.entity.Match;
import br.com.itengine.score.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MyLittle on 19/05/16.
 */
public interface MatchRepository extends CrudRepository<Match, Integer> {
    List<Match> findAll();
    Match findById(Integer id);
    List<Match> findByLeague(League league);
    List<Match> findByTeamVisitorOrTeamHome(Team teamHome,Team teamVisitor);

}
