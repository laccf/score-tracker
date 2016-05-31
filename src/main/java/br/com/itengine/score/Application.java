package br.com.itengine.score;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.itengine.score.entity.Action;
import br.com.itengine.score.entity.ActionType;
import br.com.itengine.score.entity.League;
import br.com.itengine.score.entity.Match;
import br.com.itengine.score.entity.Player;
import br.com.itengine.score.entity.Role;
import br.com.itengine.score.entity.Team;
import br.com.itengine.score.entity.User;
import br.com.itengine.score.repository.ActionRepository;
import br.com.itengine.score.repository.LeagueRepository;
import br.com.itengine.score.repository.MatchRepository;
import br.com.itengine.score.repository.PlayerRepository;
import br.com.itengine.score.repository.TeamRepository;
import br.com.itengine.score.repository.UserRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static String ROOT = "upload-dir";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("*");
            }
        };
    }


    @Bean
    public CommandLineRunner loadData(MatchRepository matchRepository,
                                      TeamRepository teamRepository,
                                      PlayerRepository playerRepository,
                                      LeagueRepository leagueRepository,
                                      ActionRepository actionRepository,
                                      UserRepository userRepository
    ) {
        return (args) -> {

            new File(ROOT).mkdir();
            User userRoot = new User();
            User userLeague = new User();
            User userLeagueTwo = new User();
            User userTeam = new User();
            User userDelegate = new User();
            User userDelegateTwo = new User();


            userRoot.setRole(Role.ROOT.toString());
            userRoot.setUsername("userroot");
            userRoot.setPassword("pass");
            userRoot.setName("Root");
            userRoot.setEmail("email@root.com");
            userRoot.setPhone("33333331");

            userLeague.setRole(Role.LEAGUE.toString());
            userLeague.setUsername("userleague");
            userLeague.setPassword("pass");
            userLeague.setName("League Admin");
            userLeague.setEmail("email@league.com");
            userLeague.setPhone("33333332");

            userLeagueTwo.setRole(Role.LEAGUE.toString());
            userLeagueTwo.setUsername("userleaguetwo");
            userLeagueTwo.setPassword("pass");
            userLeagueTwo.setName("League Admin Two");
            userLeagueTwo.setEmail("emailTwo@league.com");
            userLeagueTwo.setPhone("33333332");

            userTeam.setRole(Role.TEAM.toString());
            userTeam.setUsername("userteam");
            userTeam.setPassword("pass");
            userTeam.setName("Team Admin");
            userTeam.setEmail("email@team.com");
            userTeam.setPhone("33333333");

            userDelegate.setRole(Role.DELEGATE.toString());
            userDelegate.setUsername("userdelegate");
            userDelegate.setPassword("pass");
            userDelegate.setName("Delegate User");
            userDelegate.setEmail("email@delegate1.com");
            userDelegate.setPhone("33333334");

            userDelegateTwo.setRole(Role.DELEGATE.toString());
            userDelegateTwo.setUsername("userdelegatetwo");
            userDelegateTwo.setPassword("pass");
            userDelegateTwo.setName("Delegate User 2");
            userDelegateTwo.setEmail("email@delegate2.com");
            userDelegateTwo.setPhone("3333335");

            userRoot = userRepository.save(userRoot);
            userLeague = userRepository.save(userLeague);
            userLeagueTwo = userRepository.save(userLeagueTwo);
            userTeam = userRepository.save(userTeam);
            userDelegate = userRepository.save(userDelegate);
            userDelegateTwo = userRepository.save(userDelegateTwo);


            League leaguePernabucano = new League();
            leaguePernabucano.setName("Pernambucano's League");
            leaguePernabucano.setDate(2004);
            leaguePernabucano.setLeagueAdmin(userLeague);
            leaguePernabucano = leagueRepository.save(leaguePernabucano);

            Team teamSport = new Team();
            teamSport.setName("Sport Club");
            teamSport.setLeague(leaguePernabucano);
            teamSport.setTeamAdmin(userTeam);
            teamSport.setAddress("Ilha do Retiro");

            Team teamSanta = new Team();
            teamSanta.setName("Santa Cruz");
            teamSanta.setLeague(leaguePernabucano);
            teamSanta.setTeamAdmin(userTeam);
            teamSanta.setAddress("Arruda");

            Team teamNautico = new Team();
            teamNautico.setName("Nautico");
            teamNautico.setLeague(leaguePernabucano);
            teamNautico.setTeamAdmin(userTeam);
            teamNautico.setAddress("Aflitos");

            teamSport = teamRepository.save(teamSport);
            teamSanta = teamRepository.save(teamSanta);
            teamNautico = teamRepository.save(teamNautico);


            String[] sportNames = {"Durval Silva", "Sandro Goiano", "Alexandro Beti", "Tulio Vinicius", "Thiago Almeida"};
            String[] santaNames = {"Luiz Antonio", "Anderson Pablo", "Romeryto Lira", "Gabi Alves", "Pedro Hyvo", "Erick Costa"};
            String[] nauticoNames = {"Joao Arthur", "Italo Almeida", "Jorge Silva", "Dalton Serey", "Adalberto Cajueiro"};

            List<Player> sportPlayers = new LinkedList<>();
            List<Player> santaPlayers = new LinkedList<>();
            List<Player> nauticoPlayers = new LinkedList<>();

            for (int i = 0; i < sportNames.length; i++) {
                Player sportPlayer = new Player();
                Player santaPlayer = new Player();
                Player nauticoPlayer = new Player();

                sportPlayer.setName(sportNames[i].split(" ")[0]);
                sportPlayer.setLastName(sportNames[i].split(" ")[1]);
                sportPlayer.setTeam(teamSport);
                sportPlayer.setWeight(80);
                sportPlayer.setHeight(170);


                santaPlayer.setName(santaNames[i].split(" ")[0]);
                santaPlayer.setLastName(santaNames[i].split(" ")[1]);
                santaPlayer.setTeam(teamSanta);
                santaPlayer.setWeight(80);
                santaPlayer.setHeight(170);

                nauticoPlayer.setName(nauticoNames[i].split(" ")[0]);
                nauticoPlayer.setLastName(nauticoNames[i].split(" ")[1]);
                nauticoPlayer.setTeam(teamNautico);
                nauticoPlayer.setWeight(80);
                nauticoPlayer.setHeight(170);

                sportPlayer = playerRepository.save(sportPlayer);
                santaPlayer = playerRepository.save(santaPlayer);
                nauticoPlayer = playerRepository.save(nauticoPlayer);
                sportPlayers.add(sportPlayer);
                santaPlayers.add(santaPlayer);
                nauticoPlayers.add(nauticoPlayer);
            }
            teamSport.setPlayers(sportPlayers);
            teamSanta.setPlayers(santaPlayers);
            teamNautico.setPlayers(nauticoPlayers);
            teamRepository.save(teamSport);

            teamRepository.save(teamSanta);
            teamRepository.save(teamNautico);
            //TODO:implementar inicializacao

            Match matchNauticoSport = new Match();
            Match matchNauticoSanta = new Match();
            Match matchSportSanta = new Match();
            Match matchSportNautico = new Match();


            String sourceDate = "2012-02-29";
            String sourceDateTwo = "2012-03-20";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(sourceDate);
            Date dateTwo = format.parse(sourceDateTwo);


            matchNauticoSport.setTeamHome(teamNautico);
            matchNauticoSport.setTeamVisitor(teamSport);
            matchNauticoSport.setDelegate(userDelegate);
            matchNauticoSport.setLeague(leaguePernabucano);
            matchNauticoSport.setDateOfMatch(date);

            matchNauticoSanta.setTeamHome(teamNautico);
            matchNauticoSanta.setTeamVisitor(teamSanta);
            matchNauticoSanta.setDelegate(userDelegate);
            matchNauticoSanta.setLeague(leaguePernabucano);
            matchNauticoSanta.setDateOfMatch(date);

            matchSportSanta.setTeamHome(teamSport);
            matchSportSanta.setTeamVisitor(teamSanta);
            matchSportSanta.setDelegate(userDelegateTwo);
            matchSportSanta.setLeague(leaguePernabucano);
            matchSportSanta.setDateOfMatch(dateTwo);

            matchSportNautico.setTeamHome(teamSport);
            matchSportNautico.setTeamVisitor(teamNautico);
            matchSportNautico.setDelegate(userDelegateTwo);
            matchSportNautico.setLeague(leaguePernabucano);
            matchSportNautico.setDateOfMatch(dateTwo);
            matchSportNautico.setIsPlayed(true);


            matchRepository.save(matchNauticoSport);
            matchRepository.save(matchNauticoSanta);
            matchRepository.save(matchSportSanta);
            matchRepository.save(matchSportNautico);


            //Actions
            Action action1 = new Action();
            Action action2 = new Action();
            Action action3 = new Action();
            Action action4 = new Action();
            Action action5 = new Action();

            action1.setPlayer(teamNautico.getPlayers().get(0));
            action1.setMinute(10);
            action1.setActionType(ActionType.GOAL);

            action2.setPlayer(teamNautico.getPlayers().get(2));
            action2.setMinute(15);
            action2.setActionType(ActionType.GOAL);

            action3.setPlayer(teamNautico.getPlayers().get(1));
            action3.setMinute(20);
            action3.setActionType(ActionType.FAUL);

            action4.setPlayer(teamNautico.getPlayers().get(2));
            action4.setMinute(5);
            action4.setActionType(ActionType.GOAL);

            action5.setPlayer(teamNautico.getPlayers().get(0));
            action5.setMinute(44);
            action5.setActionType(ActionType.GOAL);

            action1 = actionRepository.save(action1);
            action2 = actionRepository.save(action2);
            action3 = actionRepository.save(action3);
            action4 = actionRepository.save(action4);
            action5 = actionRepository.save(action5);

            matchNauticoSport.setActions(new LinkedList<Action>());

            matchNauticoSport.getActions().add(action1);
            matchNauticoSport.getActions().add(action2);
            matchNauticoSport.getActions().add(action3);
            matchNauticoSport.getActions().add(action4);
            matchNauticoSport.getActions().add(action5);
            matchRepository.save(matchNauticoSport);


        };
    }

}
