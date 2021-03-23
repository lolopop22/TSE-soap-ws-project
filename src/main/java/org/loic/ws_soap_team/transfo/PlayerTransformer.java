package org.loic.ws_soap_team.transfo;

import org.loic.ws.components.PlayerSoap;
import org.loic.ws.components.PlayerSoapInfo;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerTransformer {
	
	@Autowired
	private TeamPlayerTransformer teamPlayerConvertor;
	
	/**
	 * Permet de convertir une entité JPA joueur en son équivalent soap.
	 * @param playerEntity le jouer sous le format gérable par JPA
	 * @return le joueur sous le format soap
	 */
	public PlayerSoap convertToSoapFormat(PlayerEntity playerEntity) {
		
		PlayerSoap playerSoap = new PlayerSoap();
		PlayerSoapInfo playerSoapInfo = new PlayerSoapInfo();
		
		int pId = (int)(long)playerEntity.getId();
		
		if(pId != 0) {
			playerSoap.setPid(pId);
		}
		
		playerSoapInfo.setPid(pId);
		playerSoapInfo.setName(playerEntity.getName());
		playerSoapInfo.setAge(playerEntity.getAge());
		playerSoapInfo.setCitizenship(playerEntity.getCitizenship());
		
		if(playerEntity.getTeams() != null) {
			for(PlayerTeamEntity player : playerEntity.getTeams()) {
				//PlayerTeamSoap playerTeamSoap = new PlayerTeamSoap();
				playerSoap.getTeams().add(this.teamPlayerConvertor.convertToSoapFormat(player));
			}
		}
		
		playerSoap.setPlayerSoapInfo(playerSoapInfo);
		
		return playerSoap;
		
	}
	/**
	 * Permet de convertir un joueur sous format SOAP en son équivalent JPA (entity).
	 * @param playerSoapInfo les infos du joueur provenant du web service SOAP.
	 * @return L'équivalent JPA du joueur SOAP.
	 */
	public PlayerEntity convertToEntityFormat(PlayerSoapInfo playerSoapInfo) {
		
		Long pId = Long.valueOf(playerSoapInfo.getPid());
		int age = playerSoapInfo.getAge();
		String citizenship = playerSoapInfo.getCitizenship();
		String name = playerSoapInfo.getName();
		
		PlayerEntity playerEntity = new PlayerEntity();
		
		if(pId != 0) {
			playerEntity.setId(pId);
		}
		
		//playerEntity.setId(pId);
		playerEntity.setAge(age);
		playerEntity.setCitizenship(citizenship);
		playerEntity.setName(name);
		
		return playerEntity;
	}

}
