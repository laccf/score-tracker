package br.com.itengine.score.repository;

import br.com.itengine.score.entity.Role;
import br.com.itengine.score.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thiag on 23/05/2016.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findAll();
    User findById(Integer id);
    List<User> findByRole(Role role);
}
