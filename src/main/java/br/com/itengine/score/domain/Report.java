package br.com.itengine.score.domain;

import br.com.itengine.score.entity.League;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thiag on 27/05/2016.
 */
public class Report {
    League league;
    List<TeamReport> teams;

    public Report() {
        teams = new LinkedList<>();
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<TeamReport> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamReport> teams) {
        this.teams = teams;
    }
}
