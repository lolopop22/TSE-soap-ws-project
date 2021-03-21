package org.loic.ws_soap_team.transfo;

import org.loic.ws.components.PlayerSoap;
import org.loic.ws.components.PlayerSoapInfo;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerTransformer {
	
	public PlayerSoap convertToSoapFormat(PlayerEntity playerEntity, PlayerSoap playerSoap) {
		
		PlayerSoapInfo playerSoapInfo = new PlayerSoapInfo();
		
		int id = (int)(long)playerEntity.getId();
		
		playerSoap.setPid(id);
		
		playerSoapInfo.setName(playerEntity.getName());
		playerSoapInfo.setAge(playerEntity.getAge());
		playerSoapInfo.setCitizenship(playerEntity.getCitizenship());
		
		playerSoap.setPlayerSoapInfo(playerSoapInfo);
		
		return playerSoap;
		
	}
	
	public PlayerEntity convertToEntityFormat(PlayerSoapInfo playerSoapInfo, PlayerEntity playerEntity) {
		
		playerEntity.setAge(playerSoapInfo.getAge());
		playerEntity.setCitizenship(playerSoapInfo.getCitizenship());
		playerEntity.setName(playerSoapInfo.getName());
		
		return playerEntity;
	}

}
