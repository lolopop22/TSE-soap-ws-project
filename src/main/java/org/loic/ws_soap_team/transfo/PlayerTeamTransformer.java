package org.loic.ws_soap_team.transfo;

import org.loic.ws.components.PlayerSoapInfo;
import org.loic.ws.components.PlayerTeamSoap;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerTeamTransformer {
	
	@Autowired
	private PlayerTransformer playerConvertor;

	public PlayerTeamSoap convertToSoapFormat(PlayerTeamEntity playerTeamEntity) {
		
		PlayerTeamSoap playerTeamSoap = new PlayerTeamSoap();
		
		playerTeamSoap.setPosition(playerTeamEntity.getPosition());
		
		PlayerSoapInfo playerSoapInfo = new PlayerSoapInfo();
		
		int pid = (int)(long)playerTeamEntity.getPlayer().getId();
		playerSoapInfo.setPid(pid);
		playerSoapInfo.setName(playerTeamEntity.getPlayer().getName());
		playerSoapInfo.setAge(playerTeamEntity.getPlayer().getAge());
		playerSoapInfo.setCitizenship(playerTeamEntity.getPlayer().getCitizenship());
		
		playerTeamSoap.setPlayerSoapInfo(playerSoapInfo);
		
		return playerTeamSoap;
	}
	
	public PlayerTeamEntity convertToEntityFormat(PlayerTeamSoap playerTeamSoap) {
		
		PlayerTeamEntity playerTeamEntity = new PlayerTeamEntity();
		
		playerTeamEntity.setPosition(playerTeamSoap.getPosition());
		
		PlayerEntity playerEntity = this.playerConvertor.convertToEntityFormat(playerTeamSoap.getPlayerSoapInfo());
		
		playerTeamEntity.setPlayer(playerEntity);
		
		return playerTeamEntity;
	}
	
}
