package org.loic.ws_soap_team.domains;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlayerTeamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id")
	@JsonIgnoreProperties("teams")
	PlayerEntity player;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	@JsonIgnoreProperties("players")
	TeamEntity team;
	
	String position;
	
	public PlayerTeamEntity(){

	}
	
	public PlayerTeamEntity(PlayerEntity player, String position){
		player.addTeam(this);
		this.position = position;
	}
	
}
