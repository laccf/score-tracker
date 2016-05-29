package br.com.itengine.score.domain;

import br.com.itengine.score.entity.ActionType;
import br.com.itengine.score.entity.Team;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiag on 27/05/2016.
 */
public class TeamReport {
    TeamInfo team;
    Map<ActionType, Integer> actions;

    public TeamReport() {
        actions = new HashMap<ActionType, Integer>();
    }

    public TeamInfo getTeam() {
        return team;
    }

    public void setTeam(TeamInfo team) {
        this.team = team;
    }

    public Map<ActionType, Integer> getActions() {
        return actions;
    }

    public void setActions(Map<ActionType, Integer> actions) {
        this.actions = actions;
    }
}
