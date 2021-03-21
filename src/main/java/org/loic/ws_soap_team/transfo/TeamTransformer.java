package org.loic.ws_soap_team.transfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.loic.ws.components.PlayerTeamSoap;
import org.loic.ws.components.TeamSoap;
import org.loic.ws.components.TeamSoapInfo;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.loic.ws_soap_team.domains.TeamEntity;



@Component
public class TeamTransformer {
	
	@Autowired
	private PlayerTeamTransformer playerTeamConvertor;
	
	@Autowired
	private PlayerTransformer playerConvertor;
	
	public TeamSoap convertToSoapFormat(TeamEntity teamEntity, TeamSoap teamSoap) {
		
		TeamSoapInfo teamSoapInfo = new TeamSoapInfo();
		
		int id = (int)(long)teamEntity.getId();
		teamSoap.setTid(id);
		
		teamSoapInfo.setName(teamEntity.getName());
		teamSoapInfo.setCountry(teamEntity.getCountry());
		teamSoapInfo.setType(teamEntity.getType());
		teamSoapInfo.setCaptain(teamEntity.getCaptain());
		if(teamEntity.getPlayers() != null) {
			for(PlayerTeamEntity player : teamEntity.getPlayers()) {
				PlayerTeamSoap playerTeamSoap = new PlayerTeamSoap();
				teamSoapInfo.getPlayers().add(this.playerTeamConvertor.convertToSoapFormat(player, playerTeamSoap));
			}
		}
		
		
		teamSoap.setTeamSoapInfo(teamSoapInfo);
		
		return teamSoap;
		
	}

	public TeamEntity convertToEntityFormat(TeamSoapInfo teamSoapInfo, TeamEntity teamEntity) {

		String name = teamSoapInfo.getName();
		String country = teamSoapInfo.getCountry();
		String type = teamSoapInfo.getType();
		String captain = teamSoapInfo.getCaptain();
		
		if (name != null) {
			teamEntity.setName(name);
		}
		if (country != null) {
			teamEntity.setCountry(country);
		}
		if (type != null) {
			teamEntity.setType(type);
		}
		if (captain != null) {
			teamEntity.setCaptain(captain);
		}
		
		if(teamSoapInfo.getPlayers() != null) {
			for(PlayerTeamSoap playerSoap : teamSoapInfo.getPlayers()) {
				String position = playerSoap.getPosition();
				PlayerEntity playerEntity = new PlayerEntity();
				
				this.playerConvertor.convertToEntityFormat(playerSoap.getPlayerSoapInfo(), playerEntity);
				
				PlayerTeamEntity playerTeamEntity = new PlayerTeamEntity(playerEntity, position);
				teamEntity.getPlayers().add(playerTeamEntity);
			}
		}
		
		return teamEntity;
	}

}
