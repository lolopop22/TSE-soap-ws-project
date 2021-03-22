package org.loic.ws_soap_team.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.loic.ws.components.CreatePlayerRequest;
import org.loic.ws.components.CreatePlayerResponse;
import org.loic.ws.components.DeletePlayerRequest;
import org.loic.ws.components.DeletePlayerResponse;
import org.loic.ws.components.GetPlayerRequest;
import org.loic.ws.components.GetPlayerResponse;
import org.loic.ws.components.ModifyPlayerRequest;
import org.loic.ws.components.ModifyPlayerResponse;
import org.loic.ws.components.PlayerSoap;
import org.loic.ws_soap_team.services.PlayerService;

@Endpoint
public class PlayerEndpoints {
	
	private static final String NAMESPACE_URI = "http://loic.org/ws/components";
	
	@Autowired
	private PlayerService playerService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPlayerRequest")
	@ResponsePayload
	public GetPlayerResponse getPlayer(@RequestPayload GetPlayerRequest request) {
		
		GetPlayerResponse response = new GetPlayerResponse();
		for (Integer playerId: request.getPid()) {
			System.out.println("player id in player endpoint: " + playerId);
			PlayerSoap found = playerService.findPlayer(Long.valueOf(playerId));
			System.out.println("player found in player endpoint: " + found);
			if (found != null) {
				response.getPlayerSoap().add(found);
			}
		}
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPlayerRequest")
	@ResponsePayload
	public CreatePlayerResponse createPlayer(@RequestPayload CreatePlayerRequest request) {
		
		CreatePlayerResponse response = new CreatePlayerResponse();
		System.out.println(request.getPlayerSoapInfo().getName());
		response.setPlayerSoap(playerService.createPlayer(request.getPlayerSoapInfo()));
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "modifyPlayerRequest")
	@ResponsePayload
	public ModifyPlayerResponse modifyPlayer(@RequestPayload ModifyPlayerRequest request) {
		
		ModifyPlayerResponse response = new ModifyPlayerResponse();
		Long id =Long.valueOf(request.getPlayerSoapInfo().getPid());
		response.setPlayerSoap(playerService.modifyPlayer(request.getPlayerSoapInfo(), id));
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePlayerRequest")
	@ResponsePayload
	public DeletePlayerResponse deletePlayer(@RequestPayload DeletePlayerRequest request) {
		
		long id = Long.valueOf(request.getPid());
		DeletePlayerResponse response = new DeletePlayerResponse();
		response.setDeleteResult(playerService.deletePlayer(id));
		
		return response;
	}
	

}