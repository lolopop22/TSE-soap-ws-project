package org.loic.api_soap_team.services;

import loic.io.org.ws.domains.soap.teams.TeamSoap;
import loic.io.org.ws.domains.soap.teams.TeamSoapInfo;

public interface TeamService {
	
	public TeamSoap findTeam(long teamId);

	public TeamSoap createTeam(TeamSoapInfo teamSoapInfo);

	public TeamSoap modifyTeam(TeamSoap newTeamSoap);

}
