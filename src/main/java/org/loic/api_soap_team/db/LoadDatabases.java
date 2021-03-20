package org.loic.api_soap_team.db;

import java.util.Arrays;

import org.loic.api_soap_team.dao.PlayerRepository;
import org.loic.api_soap_team.dao.TeamRepository;
import org.loic.api_soap_team.domains.Player;
import org.loic.api_soap_team.domains.PlayerTeam;
import org.loic.api_soap_team.domains.TeamEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabases {

	@Bean
	CommandLineRunner initDatabases(TeamRepository teamRepository, PlayerRepository playerRepository) {
		
		Player player1 = new Player("Neymar Jr", 28, "Brazillian");
		Player player2 = new Player("Lionel Messi", 33, "Argentine");
		
		TeamEntity psg = new TeamEntity("Paris Saint Germain", "France", "Club", "Marquinhos",
								Arrays.asList(  new PlayerTeam(player1, "Ailier gauche")));
		
		TeamEntity argNationalTeam = new TeamEntity("La Albiceleste", "Argentina", "National team", "Lionel Messi",
								Arrays.asList(  new PlayerTeam(player2, "Ailier droit")));

		
		return args -> {
			log.info("Preloading " + playerRepository.saveAll(Arrays.asList(player1, player2)));
			log.info("Preloading " + teamRepository.save(psg));
			log.info("Preloading " + teamRepository.save(argNationalTeam));
			
		};
	}
}
