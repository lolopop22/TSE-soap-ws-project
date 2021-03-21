package org.loic.ws_soap_team.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.loic.ws.components.TeamSoap;
import org.loic.ws.components.TeamSoapInfo;
import org.loic.ws_soap_team.dao.PlayerRepository;
import org.loic.ws_soap_team.dao.TeamRepository;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.loic.ws_soap_team.domains.TeamEntity;
import org.loic.ws_soap_team.exceptions.TeamNotFoundException;
import org.loic.ws_soap_team.services.TeamService;
import org.loic.ws_soap_team.transfo.TeamTransformer;


@Service
@Transactional
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TeamTransformer teamConvertor;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	/*@Override
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
		
		for(PlayerTeamEntity playerTeamEntity : newTeamEntity.getPlayers()) {
			this.playerRepository.save(playerTeamEntity.getPlayer());
		}
		
		TeamEntity teamSaved = this.teamRepository.save(newTeamEntity);
		TeamSoap teamSoap = new TeamSoap();
		return teamConvertor.convertToSoapFormat(teamSaved, teamSoap);
	};
	
	@Override
	public TeamSoap modifyTeam(TeamSoap newTeamSoap) {
		
		Long id = Long.valueOf(newTeamSoap.getTid());
		TeamEntity team = this.teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
		teamConvertor.convertToEntityFormat(newTeamSoap.getTeamSoapInfo(), team);
		
		if (team != null) {
			for(PlayerTeamEntity playerTeamEntity : team.getPlayers()) {
				this.playerRepository.save(playerTeamEntity.getPlayer());
			}
			
			return teamConvertor.convertToSoapFormat(this.teamRepository.save(team), newTeamSoap);
		}
		
		return teamConvertor.convertToSoapFormat(team, newTeamSoap);
	};
	
	@Override
	public String deleteTeam(Long teamId) {
		
		boolean isExistBeforeDelete = this.teamRepository.findById(teamId).isPresent();
		
		if(isExistBeforeDelete) {
			this.teamRepository.deleteById(teamId);
			return "Deletion succesful";
		}
		return "Deletion failed";
	}

}
