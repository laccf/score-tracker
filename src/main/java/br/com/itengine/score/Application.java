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
                                      ActionTypeRepository actionTypeRepository,
                                      RoleRepository roleRepository,
                                      UserRepository userRepository
    ) {
		return (args) -> {

            Role root = new Role();
            root.setName("ROOT");
            Role leagueAdmin = new Role();
            leagueAdmin.setName("LEAGUEADMIN");
            Role teamAdmin = new Role();
            teamAdmin.setName("TEAMADMIN");
            Role delegate = new Role();
            delegate.setName("DELEGATE");

            root = roleRepository.save(root);
            leagueAdmin = roleRepository.save(leagueAdmin);
            teamAdmin = roleRepository.save(teamAdmin);
            delegate = roleRepository.save(delegate);

            ActionType goal = new ActionType();
            goal.setName("GOAL");
            ActionType faul = new ActionType();
            goal.setName("FAUL");
            ActionType asist = new ActionType();
            goal.setName("ASIST");
            ActionType suspension = new ActionType();
            goal.setName("SUSPENSION");



            goal = actionTypeRepository.save(goal);
            faul = actionTypeRepository.save(faul);
            asist = actionTypeRepository.save(asist);
            suspension = actionTypeRepository.save(suspension);

            User userRoot = new User();
            User userLeague = new User();
            User userTeam = new User();
            User userDelegate = new User();

            userRoot.setRole(root);
            userRoot.setUsername("userroot");
            userRoot.setPassword("pass");

            userLeague.setRole(leagueAdmin);
            userLeague.setUsername("userleague");
            userLeague.setPassword("pass");

            userTeam.setRole(teamAdmin);
            userTeam.setUsername("userteam");
            userTeam.setPassword("pass");

            userDelegate.setRole(delegate);
            userDelegate.setUsername("userdelegate");
            userDelegate.setPassword("pass");

            userRoot = userRepository.save(userRoot);
            userLeague = userRepository.save(userLeague);
            userTeam = userRepository.save(userTeam);
            userDelegate = userRepository.save(userDelegate);


            League leaguePernabucano = new League();
            leaguePernabucano.setName("Pernambucano's League");
            leaguePernabucano.setDate(2004);
            leaguePernabucano.setLeagueAdmin(userLeague);
            leaguePernabucano = leagueRepository.save(leaguePernabucano);

			Team teamSport = new Team();
			teamSport.setName("Sport Club");
            teamSport.setLeague(leaguePernabucano);
            teamSport.setTeamAdmin(userTeam);

            Team teamSanta = new Team();
			teamSanta.setName("Santa Cruz");
            teamSanta.setLeague(leaguePernabucano);
            teamSanta.setTeamAdmin(userTeam);

            Team teamNautico = new Team();
            teamNautico.setName("Nautico");
            teamNautico.setLeague(leaguePernabucano);
            teamNautico.setTeamAdmin(userTeam);

            teamSport = teamRepository.save(teamSport);
            teamSanta = teamRepository.save(teamSanta);
            teamNautico = teamRepository.save(teamNautico);


            String[] sportNames = {"Durval Silva","Sandro Goiano","Alexandro Beti", "Tulio Vinicius","Thiago Almeida"};
            String[] santaNames = {"Luiz Antonio","Anderson Pablo","Romeryto Lira","Gabi Alves","Pedro Hyvo","Erick Costa"};
            String[] nauticoNames = {"Joao Arthur","Italo Almeida","Jorge Silva","Dalton Serey","Adalberto Cajueiro"};

            List<Player> sportPlayers = new LinkedList<>();
            List<Player> santaPlayers = new LinkedList<>();
            List<Player> nauticoPlayers = new LinkedList<>();

            for (int i = 0;i<sportNames.length;i++){
                Player sportPlayer = new Player();
                Player santaPlayer = new Player();
                Player nauticoPlayer = new Player();

                sportPlayer.setName(sportNames[i].split(" ")[0]);
                sportPlayer.setLastName(sportNames[i].split(" ")[1]);
                sportPlayer.setTeam(teamSport);

                santaPlayer.setName(santaNames[i].split(" ")[0]);
                santaPlayer.setLastName(santaNames[i].split(" ")[1]);
                santaPlayer.setTeam(teamSanta);

                nauticoPlayer.setName(nauticoNames[i].split(" ")[0]);
                nauticoPlayer.setLastName(nauticoNames[i].split(" ")[1]);
                nauticoPlayer.setTeam(teamNautico);


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
		};
	}

}
