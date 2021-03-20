package org.loic.ws_soap_team.transfo;

import org.springframework.stereotype.Component;
import org.loic.ws.components.TeamSoap;
import org.loic.ws.components.TeamSoapInfo;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.loic.ws_soap_team.domains.TeamEntity;



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
		/*if(teamEntity.getPlayers() != null) {
			for(PlayerTeamEntity player : teamEntity.getPlayers()) {
				PlayerEntity player_ = player.getPlayer();
				PlayerEntity newPlayer = new PlayerEntity(player_.getName(), player_.getAge(), player_.getCitizenship());
				PlayerTeamEntity newPlayerteam = new PlayerTeamEntity(newPlayer, player.getPosition());
				newPlayers.add(newPlayerteam);
				this.playerRepository.save(newPlayer);
			}
		}*/
		
		
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
