package org.loic.ws_soap_team.transfo;

import org.loic.ws.components.TeamPlayerSoap;
import org.loic.ws.components.TeamSoapInfo;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamPlayerTransformer {

	public TeamPlayerSoap convertToSoapFormat(PlayerTeamEntity playerTeamEntity) {
		
		TeamPlayerSoap teamPlayerSoap = new TeamPlayerSoap();
		
		teamPlayerSoap.setPosition(playerTeamEntity.getPosition());
		
		TeamSoapInfo teamSoapInfo = new TeamSoapInfo();
		
		int tId = (int)(long)playerTeamEntity.getTeam().getId();
		teamSoapInfo.setTId(tId);
		teamSoapInfo.setCaptain(playerTeamEntity.getTeam().getCaptain());
		teamSoapInfo.setCountry(playerTeamEntity.getTeam().getCountry());
		teamSoapInfo.setName(playerTeamEntity.getTeam().getName());
		teamSoapInfo.setType(playerTeamEntity.getTeam().getType());
		
		teamPlayerSoap.setTeamSoapInfo(teamSoapInfo);
		
		return teamPlayerSoap;
	}

}
