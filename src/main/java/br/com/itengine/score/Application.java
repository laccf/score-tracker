package br.com.itengine.score;

import br.com.itengine.score.entity.*;
import br.com.itengine.score.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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


            User userRoot = new User();
            User userLeague = new User();
            User userTeam = new User();
            User userDelegate = new User();
            User userDelegateTwo = new User();


            userRoot.setRole(Role.ROOT);
            userRoot.setUsername("userroot");
            userRoot.setPassword("pass");
            userRoot.setName("Root");
            userRoot.setEmail("email@root.com");
            userRoot.setPhone("33333331");

            userLeague.setRole(Role.LEAGUEADMIN);
            userLeague.setUsername("userleague");
            userLeague.setPassword("pass");
            userLeague.setName("League Admin");
            userLeague.setEmail("email@league.com");
            userLeague.setPhone("33333332");

            userTeam.setRole(Role.TEAMADMIN);
            userTeam.setUsername("userteam");
            userTeam.setPassword("pass");
            userTeam.setName("Team Admin");
            userTeam.setEmail("email@team.com");
            userTeam.setPhone("33333333");

            userDelegate.setRole(Role.DELEGATE);
            userDelegate.setUsername("userdelegate");
            userDelegate.setPassword("pass");
            userDelegate.setName("Delegate User");
            userDelegate.setEmail("email@delegate1.com");
            userDelegate.setPhone("33333334");

            userDelegateTwo.setRole(Role.DELEGATE);
            userDelegateTwo.setUsername("userdelegatetwo");
            userDelegateTwo.setPassword("pass");
            userDelegateTwo.setName("Delegate User 2");
            userDelegateTwo.setEmail("email@delegate2.com");
            userDelegateTwo.setPhone("3333335");

            userRoot = userRepository.save(userRoot);
            userLeague = userRepository.save(userLeague);
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

            teamSport.setDeleted(true);
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




        };
    }

}
