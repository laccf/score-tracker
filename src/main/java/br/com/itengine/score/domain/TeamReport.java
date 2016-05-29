package br.com.itengine.score.domain;

import br.com.itengine.score.entity.ActionType;
import br.com.itengine.score.entity.Team;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiag on 27/05/2016.
 */
public class TeamReport {
    Team team;
    Map<ActionType, Long> actions;

    public TeamReport() {
        actions = new HashMap<ActionType, Long>();
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Map<ActionType, Long> getActions() {
        return actions;
    }

    public void setActions(Map<ActionType, Long> actions) {
        this.actions = actions;
    }
}
