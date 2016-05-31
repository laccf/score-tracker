package br.com.itengine.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itengine.score.entity.Role;
import br.com.itengine.score.entity.User;
import br.com.itengine.score.repository.UserRepository;

/**
 * Created by thiag on 23/05/2016.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ROOT')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<User>(userRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value="/role",method = RequestMethod.GET)
    public ResponseEntity<List<User>> findByRole(@RequestParam Role role) {
        return new ResponseEntity<List<User>>(userRepository.findByRole(role), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable Integer id) {
        user.setId(id);
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
    }



    @RequestMapping(value="",method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
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
