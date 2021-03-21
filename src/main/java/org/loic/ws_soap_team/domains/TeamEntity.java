package org.loic.ws_soap_team.domains;

import java.util.ArrayList;
import java.util.List;
/*import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;*/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "teams")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String country;
	
	private String type;
	
	private String captain;
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties(value = {"team"}, allowSetters = true)
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<PlayerTeamEntity> players = new ArrayList<>();
		
	public TeamEntity(){
		
	}
	
	public void addPlayer(PlayerTeamEntity player) {
		players.add(player);
		player.setTeam(this);
	}
	
	public TeamEntity(long id, String name, String country, String type, String captain, List<PlayerTeamEntity> players){
		if(id != 0) {
			this.id = id;  //ceci me permet en quelque sorte de modifier une équipe qui existe déjà en base
							//si on passe en paramètre id=0 alors l'équipe sera crée en dans la bdd.
		}
		this.name = name;
		this.country = country;
		this.type = type;
		this.captain = captain;
		for(PlayerTeamEntity player : players) {
			addPlayer(player);
		}
	}

}
