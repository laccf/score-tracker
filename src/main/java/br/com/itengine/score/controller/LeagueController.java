package br.com.itengine.score.controller;

import br.com.itengine.score.entity.League;
import br.com.itengine.score.entity.User;
import br.com.itengine.score.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thiag on 23/05/2016.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/leagues")
public class LeagueController {

    @Autowired
    LeagueRepository leagueRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<League>> findAll() {
        return new ResponseEntity<List<League>>(leagueRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="",method = RequestMethod.PUT)
    public ResponseEntity<League> update(League league) {
        if(leagueRepository.exists(league.getId())){
            return new ResponseEntity<League>(leagueRepository.save(league), HttpStatus.OK);
        }else{
            return new ResponseEntity<League>(league, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<League> create(League league) {
        if(!leagueRepository.exists(league.getId())){
            return new ResponseEntity<League>(leagueRepository.save(league), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<League>(league, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="",method = RequestMethod.DELETE)
    public ResponseEntity<League> delete(League league) {
        if(leagueRepository.exists(league.getId())){
            leagueRepository.delete(league);
            return new ResponseEntity<League>(league, HttpStatus.OK);
        }else{
            return new ResponseEntity<League>(league, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<League> delete(@PathVariable Integer id) {
        if(leagueRepository.exists(id)){
            League league = leagueRepository.findOne(id);
            leagueRepository.delete(id);
            return new ResponseEntity<League>(league, HttpStatus.OK);
        }else{
            return new ResponseEntity<League>(new League(), HttpStatus.NOT_FOUND);
        }
    }


}
