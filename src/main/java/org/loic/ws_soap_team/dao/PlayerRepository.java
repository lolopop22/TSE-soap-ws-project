package org.loic.ws_soap_team.dao;

import org.loic.ws_soap_team.domains.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
