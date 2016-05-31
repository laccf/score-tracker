package br.com.itengine.score.controller;

import br.com.itengine.score.entity.League;
import br.com.itengine.score.entity.Player;
import br.com.itengine.score.repository.LeagueRepository;
import br.com.itengine.score.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thiag on 24/05/2016.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<Player>> findAll() {
        return new ResponseEntity<List<Player>>(playerRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Player> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<Player>(playerRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value="",method = RequestMethod.PUT)
    public ResponseEntity<Player> update(@RequestBody Player player) {
        if(playerRepository.exists(player.getId())){
            return new ResponseEntity<Player>(playerRepository.save(player), HttpStatus.OK);
        }else{
            return new ResponseEntity<Player>(player, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<Player> create(@RequestBody Player player) {
        if(null == player.getId()){
            return new ResponseEntity<Player>(playerRepository.save(player), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Player>(player, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="",method = RequestMethod.DELETE)
    public ResponseEntity<Player> delete(Player player) {
        if(playerRepository.exists(player.getId())){
            playerRepository.delete(player);
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        }else{
            return new ResponseEntity<Player>(player, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Player> delete(@PathVariable Integer id) {
        if(playerRepository.exists(id)){
            Player player = playerRepository.findOne(id);
            playerRepository.delete(id);
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        }else{
            return new ResponseEntity<Player>(new Player(), HttpStatus.NOT_FOUND);
        }
    }

}
