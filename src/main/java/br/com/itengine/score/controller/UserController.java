package br.com.itengine.score.controller;

import br.com.itengine.score.entity.User;
import br.com.itengine.score.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by thiag on 23/05/2016.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="",method = RequestMethod.PUT)
    public ResponseEntity<User> update(User user) {
        if(userRepository.exists(user.getId())){
            return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<User> create(User user) {
        if(!userRepository.exists(user.getId())){
            return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="",method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(User user) {
        if(userRepository.exists(user.getId())){
            userRepository.delete(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        if(userRepository.exists(id)){
            User user = userRepository.findOne(id);
            userRepository.delete(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
        }
    }


}
