package org.loic.api_soap_team.dao;

import org.loic.api_soap_team.domains.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
