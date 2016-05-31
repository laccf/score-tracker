package br.com.itengine.score.controller;

import br.com.itengine.score.entity.Match;
import br.com.itengine.score.entity.Player;
import br.com.itengine.score.repository.MatchRepository;
import br.com.itengine.score.repository.PlayerRepository;
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
@RequestMapping("/matches")
public class MatchController {


    @Autowired
    MatchRepository matchRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<Match>> findAll() {
        return new ResponseEntity<List<Match>>(matchRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Match> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<Match>(matchRepository.findById(id), HttpStatus.OK);
    }


    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Match> update(@RequestBody Match match,@PathVariable Integer id) {
        match.setId(id);
        return new ResponseEntity<Match>(matchRepository.save(match), HttpStatus.OK);
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<Match> create(@RequestBody Match match) {
        return new ResponseEntity<Match>(matchRepository.save(match), HttpStatus.CREATED);
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
