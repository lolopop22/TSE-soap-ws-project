package org.loic.ws_soap_team.transfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.loic.ws.components.PlayerTeamSoap;
import org.loic.ws.components.TeamSoap;
import org.loic.ws.components.TeamSoapInfo;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.loic.ws_soap_team.domains.TeamEntity;



@Component
public class TeamTransformer {
	
	@Autowired
	private PlayerTeamTransformer playerTeamConvertor;
	
	/**
	 * Permet de convertir une entité JPA équipe en son équivalent soap.
	 * @param teamEntity l'équipe sous le format gérable par JPA.
	 * @return L'équipe sous le format soap.
	 */
	public TeamSoap convertToSoapFormat(TeamEntity teamEntity) {
		
		TeamSoap teamSoap = new TeamSoap();
		
		TeamSoapInfo teamSoapInfo = new TeamSoapInfo();
		
		int tId = (int)(long)teamEntity.getId();
		teamSoap.setTId(tId);
		
		teamSoapInfo.setTId(tId);
		teamSoapInfo.setName(teamEntity.getName());
		teamSoapInfo.setCountry(teamEntity.getCountry());
		teamSoapInfo.setType(teamEntity.getType());
		teamSoapInfo.setCaptain(teamEntity.getCaptain());
		
		if(teamEntity.getPlayers() != null) {
			for(PlayerTeamEntity player : teamEntity.getPlayers()) {
				//PlayerTeamSoap playerTeamSoap = new PlayerTeamSoap();
				teamSoap.getPlayers().add(this.playerTeamConvertor.convertToSoapFormat(player));
			}
		}
		
		teamSoap.setTeamSoapInfo(teamSoapInfo);
		
		return teamSoap;
		
	}
	/**
	 * Permet de convertir une équipe sous format SOAP équipe en son équivalent JPA (entity).
	 * @param teamSoap L'équipe sous le format soap.
	 * @return l'équipe sous le format gérable par JPA.
	 */
	public TeamEntity convertToEntityFormat(TeamSoap teamSoap) {
		
		long tId = Long.valueOf(teamSoap.getTId());
		String name = teamSoap.getTeamSoapInfo().getName();
		String country = teamSoap.getTeamSoapInfo().getCountry();
		String type = teamSoap.getTeamSoapInfo().getType();
		String captain = teamSoap.getTeamSoapInfo().getCaptain();
		
		List<PlayerTeamEntity> newPlayers = new ArrayList<>();
		
		if(teamSoap.getPlayers() != null) {
			for(PlayerTeamSoap playerTeamSoap : teamSoap.getPlayers()) {
				PlayerTeamEntity playerTeamEntity = this.playerTeamConvertor.convertToEntityFormat(playerTeamSoap);				
				newPlayers.add(playerTeamEntity);
			}
		}
		
		TeamEntity teamEntity = new TeamEntity(tId, name, country, type, captain, newPlayers);
		return teamEntity;
	}
	

}
