package br.com.itengine.score.controller;

import br.com.itengine.score.domain.Report;
import br.com.itengine.score.domain.TeamReport;
import br.com.itengine.score.entity.*;
import br.com.itengine.score.repository.LeagueRepository;
import br.com.itengine.score.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by thiag on 27/05/2016.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    LeagueRepository leagueRepository;

    @RequestMapping(value="",method = RequestMethod.GET)
    public List<Report> findAll() {
        List<League> leagues = leagueRepository.findByIsDeletedFalse();
        List<Report> reports = new LinkedList<>();
        for (League league:leagues) {
            List<Match> matchs = matchRepository.findByLeague(league);
            Map<Team, List<Action> > teamActions = new HashMap<>();
            Report report = null;
            for (Match match: matchs) {
                report = new Report();
                report.setLeague(league);
                report.setTeams(new LinkedList<TeamReport>());

                if(teamActions.containsKey(match.getTeamHome())){
                    teamActions.get(match.getTeamHome()).addAll(returnActionsByTeam(match,match.getTeamHome()));
                }else{
                    teamActions.put(match.getTeamHome(),new LinkedList<Action>());
                }

                if(teamActions.containsKey(match.getTeamVisitor())){
                    teamActions.get(match.getTeamVisitor()).addAll(returnActionsByTeam(match,match.getTeamVisitor()));
                }else{
                    teamActions.put(match.getTeamVisitor(),new LinkedList<Action>());
                }
                for (Team team: teamActions.keySet()) {
                    Map<ActionType, Long> counts = teamActions.get(team).stream().collect(Collectors.groupingBy(e -> e.getActionType(), Collectors.counting()));

                    TeamReport teamReport = new TeamReport();
                    teamReport.setTeam(team);
                    teamReport.setActions(counts);
                    report.getTeams().add(teamReport);

                }
            }
            reports.add(report);
        }
        return reports;
    }

    private List<Action> returnActionsByTeam(Match match, Team team) {
        List<Action> actions = new LinkedList<>();
        if (team.equals(match.getTeamHome())){
            for (Action action : match.getActions()){
                if(match.getTeamHome().getPlayers().contains(action.getPlayer())){
                    actions.add(action);
                }
            }
        }if(team.equals(match.getTeamVisitor())){
            for (Action action : match.getActions()){
                if(match.getTeamVisitor().getPlayers().contains(action.getPlayer())){
                    actions.add(action);
                }
            }
        }
        return  actions;
    }


}
