package org.loic.api_soap_team.services.impl;

import org.loic.api_soap_team.dao.TeamRepository;
import org.loic.api_soap_team.domains.TeamEntity;
import org.loic.api_soap_team.exceptions.TeamNotFoundException;
import org.loic.api_soap_team.services.TeamService;
import org.loic.api_soap_team.transfo.TeamTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import loic.io.org.ws.domains.soap.teams.TeamSoap;
import loic.io.org.ws.domains.soap.teams.TeamSoapInfo;


@Service
@Transactional
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TeamTransformer teamConvertor;
	
	/*@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<TeamEntity> findAllTeams() {
		
		return this.teamRepository.findAll();
	}*/

	@Override
	@Transactional(readOnly = true)
	public TeamSoap findTeam(long teamId) {
		
		TeamEntity teamEntity = this.teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
		
		System.out.println("team found in team sevice impl: " + teamEntity);
		
		if (teamEntity != null) {
			TeamSoap teamSoap = new TeamSoap();
			return teamConvertor.convertToSoapFormat(teamEntity, teamSoap);
		}
		
		return null;
	}
	
	@Override
	public TeamSoap createTeam(TeamSoapInfo teamSoapInfo) {
		
		TeamEntity newTeamEntity = new TeamEntity();
		teamConvertor.convertToEntityFormat(teamSoapInfo, newTeamEntity);
		TeamEntity teamSaved = this.teamRepository.save(newTeamEntity);
		TeamSoap teamSoap = new TeamSoap();
		return teamConvertor.convertToSoapFormat(teamSaved, teamSoap);
	};
	
	@Override
	public TeamSoap modifyTeam(TeamSoap newTeamSoap) {
		
		Long id = Long.valueOf(newTeamSoap.getTid());
		TeamEntity team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
		
		if (team != null) {
			String newName = newTeamSoap.getTeamSoapInfo().getName();
			String newCountry = newTeamSoap.getTeamSoapInfo().getCountry();
			String newType = newTeamSoap.getTeamSoapInfo().getType();
			String newCaptain = newTeamSoap.getTeamSoapInfo().getCaptain();
			
			if (newName != null) {
				team.setName(newName);
			}
			if (newCountry != null) {
				team.setCountry(newCountry);
			}
			if (newType != null) {
				team.setType(newType);
			}
			if (newCaptain != null) {
				team.setCaptain(newCaptain);
			}
			
			return teamConvertor.convertToSoapFormat(this.teamRepository.save(team), newTeamSoap);
		}
		
		return teamConvertor.convertToSoapFormat(team, newTeamSoap);
	};
	
	public String deleteTeam(Long teamId) {
		
		boolean isExistBeforeDelete = this.teamRepository.findById(teamId).isPresent();
		
		if(isExistBeforeDelete) {
			this.teamRepository.deleteById(teamId);
			return "Deletion succesful";
		}
		return "Deletion failed";
	};
}
