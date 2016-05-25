package br.com.itengine.score.entity;//
// This file was generated by the JPA Modeler
//

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Action implements Serializable {

    private ActionType actionType;

    private Player player;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Long id;

    private String minute;

    public Action() {

    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMinute() {
        return this.minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
