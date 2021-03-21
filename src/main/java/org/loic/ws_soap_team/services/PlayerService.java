package org.loic.ws_soap_team.services;

import org.loic.ws.components.PlayerSoap;
import org.loic.ws.components.PlayerSoapInfo;

public interface PlayerService {

	public PlayerSoap findPlayer(Long valueOf);

	public PlayerSoap createPlayer(PlayerSoapInfo playerSoapInfo);

	public PlayerSoap modifyPlayer(PlayerSoapInfo playerSoapInfo, Long id);

	public String deletePlayer(long id);

	
	
}
