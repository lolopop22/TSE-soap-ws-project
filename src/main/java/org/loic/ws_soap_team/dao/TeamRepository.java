package org.loic.ws_soap_team.dao;

import org.loic.ws_soap_team.domains.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
