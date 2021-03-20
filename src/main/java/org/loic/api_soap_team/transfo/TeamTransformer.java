package org.loic.api_soap_team.transfo;

import org.springframework.stereotype.Component;
import org.loic.api_soap_team.domains.TeamEntity;

import loic.io.org.ws.domains.soap.teams.TeamSoap;
import loic.io.org.ws.domains.soap.teams.TeamSoapInfo;

@Component
public class TeamTransformer {
	
	public TeamSoap convertToSoapFormat(TeamEntity teamEntity, TeamSoap teamSoap) {
		
		TeamSoapInfo teamSoapInfo = new TeamSoapInfo();
		
		int id = (int)(long)teamEntity.getId();
		teamSoap.setTid(id);
		
		teamSoapInfo.setName(teamEntity.getName());
		teamSoapInfo.setCountry(teamEntity.getCountry());
		teamSoapInfo.setType(teamEntity.getType());
		teamSoapInfo.setCaptain(teamEntity.getCaptain());
		
		teamSoap.setTeamSoapInfo(teamSoapInfo);
		
		return teamSoap;
		
	}

	public TeamEntity convertToEntityFormat(TeamSoapInfo teamSoapInfo, TeamEntity teamEntity) {

		teamEntity.setName(teamSoapInfo.getName());
		teamEntity.setCountry(teamSoapInfo.getCountry());
		teamEntity.setType(teamSoapInfo.getType());
		teamEntity.setCaptain(teamSoapInfo.getCaptain());
		
		return teamEntity;
	}

}
