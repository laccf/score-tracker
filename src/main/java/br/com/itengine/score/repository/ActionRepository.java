package br.com.itengine.score.repository;

import br.com.itengine.score.entity.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MyLittle on 19/05/16.
 */
public interface ActionRepository extends CrudRepository<Action, Integer> {
    List<Action> findAll();
    Action findById(Integer id);

}
