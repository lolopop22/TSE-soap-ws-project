package org.loic.ws_soap_team.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.loic.ws.components.CreateTeamRequest;
import org.loic.ws.components.CreateTeamResponse;
import org.loic.ws.components.DeleteTeamRequest;
import org.loic.ws.components.DeleteTeamResponse;
import org.loic.ws.components.GetTeamRequest;
import org.loic.ws.components.GetTeamResponse;
import org.loic.ws.components.ModifyTeamRequest;
import org.loic.ws.components.ModifyTeamResponse;
import org.loic.ws.components.TeamSoap;
import org.loic.ws_soap_team.services.TeamService;

@Endpoint
public class TeamEndpoint {
	
	private static final String NAMESPACE_URI = "http://loic.org/ws/components";
	
	@Autowired
	private TeamService teamService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTeamRequest")
	@ResponsePayload
	public GetTeamResponse getTeam(@RequestPayload GetTeamRequest request) {
		
		GetTeamResponse response = new GetTeamResponse();
		for (Integer teamId: request.getTid()) {
			System.out.println("team id in team endpoint: " + teamId);
			TeamSoap found = teamService.findTeam(Long.valueOf(teamId));
			System.out.println("team found in team endpoint: " + found);
			if (found != null) {
				response.getTeamSoap().add(found);
			}
		}
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createTeamRequest")
	@ResponsePayload
	public CreateTeamResponse createTeam(@RequestPayload CreateTeamRequest request) {
		
		CreateTeamResponse response = new CreateTeamResponse();
		response.setTeamSoap(teamService.createTeam(request.getTeamSoapInfo()));
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "modifyTeamRequest")
	@ResponsePayload
	public ModifyTeamResponse modifyTeam(@RequestPayload ModifyTeamRequest request) {
		
		ModifyTeamResponse response = new ModifyTeamResponse();
		response.setTeamSoap(teamService.modifyTeam(request.getTeamSoap()));
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTeamRequest")
	@ResponsePayload
	public DeleteTeamResponse deleteTeam(@RequestPayload DeleteTeamRequest request) {
		
		long id = Long.valueOf(request.getTid());
		DeleteTeamResponse response = new DeleteTeamResponse();
		response.setDeleteResult(teamService.deleteTeam(id));
		
		return response;
	}
	

}
