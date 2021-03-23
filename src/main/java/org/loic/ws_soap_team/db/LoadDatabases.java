package org.loic.ws_soap_team.db;

import java.util.Arrays;

import org.loic.ws_soap_team.dao.PlayerRepository;
import org.loic.ws_soap_team.dao.TeamRepository;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.loic.ws_soap_team.domains.TeamEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabases {

	
	/**
	 * Permet d'initialiser notre base de données h2.
	 * @param teamRepository l'interface JPA qui gère les équipes
	 * @param playerRepository l'interface JPA qui gère les joueurs
	 * @return
	 */
	@Bean
	CommandLineRunner initDatabases(TeamRepository teamRepository, PlayerRepository playerRepository) {
		
		PlayerEntity player1 = new PlayerEntity("Neymar Jr", 28, "Brazillian");
		PlayerEntity player2 = new PlayerEntity("Lionel Messi", 33, "Argentine");
		
		TeamEntity psg = new TeamEntity(0, "Paris Saint Germain", "France", "Club", "Marquinhos",
								Arrays.asList(  new PlayerTeamEntity(player1, "Ailier gauche")));
		
		TeamEntity argNationalTeam = new TeamEntity(0, "La Albiceleste", "Argentina", "National team", "Lionel Messi",
								Arrays.asList(  new PlayerTeamEntity(player2, "Ailier droit")));

		
		return args -> {
			log.info("Preloading " + playerRepository.saveAll(Arrays.asList(player1, player2)));
			log.info("Preloading " + teamRepository.save(psg));
			log.info("Preloading " + teamRepository.save(argNationalTeam));
			
		};
	}
}
