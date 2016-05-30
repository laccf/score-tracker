package br.com.itengine.score.controller;

import br.com.itengine.score.entity.League;
import br.com.itengine.score.entity.Match;
import br.com.itengine.score.entity.Player;
import br.com.itengine.score.entity.Team;
import br.com.itengine.score.repository.MatchRepository;
import br.com.itengine.score.repository.PlayerRepository;
import br.com.itengine.score.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thiag on 24/05/2016.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teams")
public class TeamController {


    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MatchRepository matchRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<Team>> findAll() {
        return new ResponseEntity<List<Team>>(teamRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Team> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<Team>(teamRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value="",method = RequestMethod.PUT)
    public ResponseEntity<Team> update(Team team) {
        if(teamRepository.exists(team.getId())){
            return new ResponseEntity<Team>(teamRepository.save(team), HttpStatus.OK);
        }else{
            return new ResponseEntity<Team>(team, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<Team> create(@RequestBody Team team) {
        if(null == team.getId()){
            return new ResponseEntity<Team>(teamRepository.save(team), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Team>(team, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="",method = RequestMethod.DELETE)
    public ResponseEntity<Team> delete(Team team) {
        if(teamRepository.exists(team.getId())){
            teamRepository.delete(team);
            return new ResponseEntity<Team>(team, HttpStatus.OK);
        }else{
            return new ResponseEntity<Team>(team, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Team> delete(@PathVariable Integer id) {
        if(teamRepository.exists(id)){
            Team team = teamRepository.findOne(id);
            List<Match> matches = matchRepository.findByTeamVisitorOrTeamHome(team,team);
            for (Match match: matches ) {
                matchRepository.delete(match);
            }
            for (Player player: team.getPlayers()
                    ) {
                playerRepository.delete(player);

            }
            teamRepository.delete(team);
            return new ResponseEntity<Team>(team, HttpStatus.OK);
        }else{
            return new ResponseEntity<Team>(new Team(), HttpStatus.NOT_FOUND);
        }
    }



}
