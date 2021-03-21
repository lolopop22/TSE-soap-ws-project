package org.loic.ws_soap_team.services.impl;

import java.util.List;

import org.loic.ws.components.PlayerSoap;
import org.loic.ws.components.PlayerSoapInfo;
import org.loic.ws_soap_team.dao.PlayerRepository;
import org.loic.ws_soap_team.domains.PlayerEntity;
import org.loic.ws_soap_team.domains.PlayerTeamEntity;
import org.loic.ws_soap_team.exceptions.PlayerNotFoundException;
import org.loic.ws_soap_team.services.PlayerService;
import org.loic.ws_soap_team.transfo.PlayerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PlayerTransformer playerConvertor;
	
	@Override
	@Transactional(readOnly = true)
	public PlayerSoap findPlayer(Long playerId) {
		
		PlayerEntity playerEntity = this.playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException(playerId));
		
		System.out.println("player found in player sevice impl: " + playerEntity);
		
		if (playerEntity != null) {
			return playerConvertor.convertToSoapFormat(playerEntity);
		}
		
		return null;
	}
	
	
	@Override
	public PlayerSoap createPlayer(PlayerSoapInfo newPlayerSoapInfo) {
		
		PlayerEntity newPlayerEntity = this.playerConvertor.convertToEntityFormat(newPlayerSoapInfo);
		
		PlayerEntity newPlayerEntitySaved = this.playerRepository.save(newPlayerEntity);
		
		return this.playerConvertor.convertToSoapFormat(newPlayerEntitySaved);
	};
	
	@Override
	public PlayerSoap modifyPlayer(PlayerSoapInfo modifiedPlayerSoapInfo, Long id) {
		
		PlayerEntity player = this.playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
		
		if (player != null) {
			
			PlayerEntity modifiedPlayerEntity = this.playerConvertor.convertToEntityFormat(modifiedPlayerSoapInfo);
			
			PlayerEntity modifiedPlayerEntitySaved = this.playerRepository.save(modifiedPlayerEntity);
			
			return this.playerConvertor.convertToSoapFormat(modifiedPlayerEntitySaved);
		}
		
		return this.playerConvertor.convertToSoapFormat(player);
	};
	
	
	public String deletePlayer(long id) {
		
		PlayerEntity player = this.playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
		
		if (player !=null) {
			List<PlayerTeamEntity> teams = player.getTeams();
			System.out.println("teams.size: " + teams.size());
			if(!teams.isEmpty()) {
				for(int i=0; i<teams.size(); i++) {
					player.removeTeam(teams.get(i));
				}
			}
			player = this.playerRepository.save(player);
			this.playerRepository.deleteById(id);
			return "Deletion succesful";
		}
		
		return "Player not Found";
		
	};

}
