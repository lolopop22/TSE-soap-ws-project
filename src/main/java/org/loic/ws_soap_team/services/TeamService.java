package org.loic.ws_soap_team.services;

import org.loic.ws.components.TeamSoap;
import org.loic.ws.components.TeamSoapInfo;

public interface TeamService {
	
	public TeamSoap findTeam(long teamId);

	public TeamSoap createTeam(TeamSoap newTeamSoap);

	public TeamSoap modifyTeam(TeamSoap teamSoap, long teamId);

	public String deleteTeam(Long teamId);


}
