package org.loic.api_soap_team.endpoints;

import org.loic.api_soap_team.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import loic.io.org.ws.domains.soap.teams.CreateTeamRequest;
import loic.io.org.ws.domains.soap.teams.CreateTeamResponse;
import loic.io.org.ws.domains.soap.teams.GetTeamRequest;
import loic.io.org.ws.domains.soap.teams.GetTeamResponse;
import loic.io.org.ws.domains.soap.teams.ModifyTeamRequest;
import loic.io.org.ws.domains.soap.teams.ModifyTeamResponse;
import loic.io.org.ws.domains.soap.teams.TeamSoap;

@Endpoint
public class TeamEndpoint {
	
	private static final String NAMESPACE_URI = "http://org.io.loic/ws/domains/soap/teams";
	
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
	

}
