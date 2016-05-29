package br.com.itengine.score.entity;//
// This file was generated by the JPA Modeler
//

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Action implements Serializable {

    private ActionType actionType;

    @ManyToOne( targetEntity = Player.class)
    private Player player;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Integer id;

    private boolean isDeleted;

    private Integer minute;

    public Action() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }
}
