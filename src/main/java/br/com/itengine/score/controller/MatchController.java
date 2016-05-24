package br.com.itengine.score.controller;

import br.com.itengine.score.entity.Match;
import br.com.itengine.score.entity.Player;
import br.com.itengine.score.repository.MatchRepository;
import br.com.itengine.score.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by thiag on 24/05/2016.
 */
@RestController
@RequestMapping(value = "/player")
public class MatchController {


    @Autowired
    MatchRepository matchRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<Match>> findAll() {
        return new ResponseEntity<List<Match>>(matchRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="",method = RequestMethod.PUT)
    public ResponseEntity<Match> update(Match match) {
        if(matchRepository.exists(match.getId())){
            return new ResponseEntity<Match>(matchRepository.save(match), HttpStatus.OK);
        }else{
            return new ResponseEntity<Match>(match, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<Match> create(Match match) {
        if(!matchRepository.exists(match.getId())){
            return new ResponseEntity<Match>(matchRepository.save(match), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Match>(match, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="",method = RequestMethod.DELETE)
    public ResponseEntity<Match> delete(Match match) {
        if(matchRepository.exists(match.getId())){
            matchRepository.delete(match);
            return new ResponseEntity<Match>(match, HttpStatus.OK);
        }else{
            return new ResponseEntity<Match>(match, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Match> delete(@PathVariable Integer id) {
        if(matchRepository.exists(id)){
            Match match = matchRepository.findOne(id);
            matchRepository.delete(id);
            return new ResponseEntity<Match>(match, HttpStatus.OK);
        }else{
            return new ResponseEntity<Match>(new Match(), HttpStatus.NOT_FOUND);
        }
    }

}
