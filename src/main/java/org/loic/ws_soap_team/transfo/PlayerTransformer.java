package org.loic.ws_soap_team.transfo;

import org.loic.ws.components.PlayerSoap;
import org.loic.ws.components.PlayerSoapInfo;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerTransformer {
	
	public PlayerSoap convertToSoapFormat(PlayerEntity playerEntity) {
		
		PlayerSoap playerSoap = new PlayerSoap();
		PlayerSoapInfo playerSoapInfo = new PlayerSoapInfo();
		
		int pId = (int)(long)playerEntity.getId();
		
		if(pId != 0) {
			playerSoap.setPid(pId);
		}
		
		playerSoapInfo.setName(playerEntity.getName());
		playerSoapInfo.setAge(playerEntity.getAge());
		playerSoapInfo.setCitizenship(playerEntity.getCitizenship());
		
		playerSoap.setPlayerSoapInfo(playerSoapInfo);
		
		return playerSoap;
		
	}
	
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
