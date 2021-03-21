package org.loic.ws_soap_team.transfo;

import org.loic.ws.components.PlayerSoapInfo;
import org.loic.ws.components.PlayerTeamSoap;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerTeamTransformer {

	public PlayerTeamSoap convertToSoapFormat(PlayerTeamEntity playerTeamEntity, PlayerTeamSoap playerTeamSoap) {
		
		playerTeamSoap.setPosition(playerTeamEntity.getPosition());
		
		PlayerSoapInfo playerSoapInfo = new PlayerSoapInfo();
		
		playerSoapInfo.setName(playerTeamEntity.getPlayer().getName());
		playerSoapInfo.setAge(playerTeamEntity.getPlayer().getAge());
		playerSoapInfo.setCitizenship(playerTeamEntity.getPlayer().getCitizenship());
		
		playerTeamSoap.setPlayerSoapInfo(playerSoapInfo);
		
		return playerTeamSoap;
	}
	
	/*public PlayerTeamEntity convertToEntityFormat(PlayerTeamSoap playerTeamSoap, PlayerTeamEntity playerTeamEntity) {
		
		playerTeamEntity.setPosition(playerTeamSoap.getPosition());
		
		PlayerSoapInfo playerSoapInfo = playerTeamSoap.getPlayerSoapInfo();
		
		playerTeamEntity.setName(playerTeamEntity.getPlayer().getName());
		playerSoapInfo.setAge(playerTeamEntity.getPlayer().getAge());
		playerSoapInfo.setCitizenship(playerTeamEntity.getPlayer().getCitizenship());
		
		playerTeamSoap.setPlayerInfo(playerSoapInfo);
		
		return playerTeamEntity;
	}*/
	
}
