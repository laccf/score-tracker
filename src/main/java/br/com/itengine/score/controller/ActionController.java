package br.com.itengine.score.controller;

import br.com.itengine.score.entity.Action;
import br.com.itengine.score.entity.Match;
import br.com.itengine.score.repository.ActionRepository;
import br.com.itengine.score.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Thiago Almeida on 30/05/2016.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    MatchRepository matchRepository;


    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<Action>> findAll() {
        return new ResponseEntity<List<Action>>(actionRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Action> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<Action>(actionRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Action> update(Action action) {
        if (actionRepository.exists(action.getId())) {
            return new ResponseEntity<Action>(actionRepository.save(action), HttpStatus.OK);
        } else {
            return new ResponseEntity<Action>(action, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Action> delete(@PathVariable("id") Integer id) {
        if(actionRepository.exists(id)){
            Action action = actionRepository.findOne(id);
            Match match = matchRepository.findByActionsContaining(action);
            match.getActions().remove(action);
            matchRepository.save(match);
            actionRepository.delete(action);
            return new ResponseEntity<Action>(action, HttpStatus.OK);
        }else{
            return new ResponseEntity<Action>(new Action(), HttpStatus.NOT_FOUND);
        }
    }

}
