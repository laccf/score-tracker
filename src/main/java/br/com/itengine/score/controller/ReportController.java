package br.com.itengine.score.controller;

import br.com.itengine.score.domain.LeagueInfo;
import br.com.itengine.score.domain.Report;
import br.com.itengine.score.domain.TeamInfo;
import br.com.itengine.score.domain.TeamReport;
import br.com.itengine.score.entity.*;
import br.com.itengine.score.repository.LeagueRepository;
import br.com.itengine.score.repository.MatchRepository;
import br.com.itengine.score.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by thiag.
 */
@RestController
@RequestMapping("/report")
@PreAuthorize("isAnonymous()")
public class ReportController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    LeagueRepository leagueRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public List<Report> findAll() {
        List<League> leagues = leagueRepository.findAll();
        List<Report> reports = new LinkedList<>();

        for (League league: leagues) {
            List<Action> actions = getActions(matchRepository.findByLeague(league));
            List<Team> teams = teamRepository.findAll();
            List<TeamReport> retorno = new LinkedList<>();

            for (Team team: teams ) {
                retorno.add(getTeamReport(team,actions));
            }
            Report report = new Report();
            LeagueInfo leagueInfo = new LeagueInfo();
            leagueInfo.setName(league.getName());
            leagueInfo.setDate(league.getDate());
            report.setLeague(leagueInfo);
            report.setTeams(retorno);
            reports.add(report);
        }
        return reports;
    }

    private List<Action> getActions(List<Match> matchs){
        List<Action> actions = new LinkedList<Action>();
        for (Match match: matchs) {
            actions.addAll(match.getActions());
        }
        return actions;
    }

    private TeamReport getTeamReport(Team team, List<Action> actions){
        TeamReport teamReport = new TeamReport();
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setId(team.getId());
        teamInfo.setName(team.getName());
        teamReport.setTeam(teamInfo);
        Map<ActionType, Integer> map = new HashMap<>();
        map.put(ActionType.ASIST,0);
        map.put(ActionType.FAUL,0);
        map.put(ActionType.GOAL,0);
        map.put(ActionType.SUSPENSION,0);
        for (Action action: actions){
            if(teamRepository.findByPlayersContaining(action.getPlayer()).getId() == team.getId()){
                map.put(action.getActionType(),1+map.get(action.getActionType()));
            }
        }
        teamReport.setActions(map);
        return teamReport;
    }

}
