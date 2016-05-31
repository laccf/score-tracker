package br.com.itengine.score.domain;

import br.com.itengine.score.entity.League;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thiag.
 */
public class Report {
    LeagueInfo league;
    List<TeamReport> teams;

    public Report() {
        teams = new LinkedList<>();
    }

    public LeagueInfo getLeague() {
        return league;
    }

    public void setLeague(LeagueInfo league) {
        this.league = league;
    }

    public List<TeamReport> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamReport> teams) {
        this.teams = teams;
    }
}
