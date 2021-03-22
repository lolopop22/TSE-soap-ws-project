package org.loic.ws_soap_team;

import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

import javax.xml.transform.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loic.ws_soap_team.dao.TeamRepository;
import org.loic.ws_soap_team.domains.TeamEntity;
import org.loic.ws_soap_team.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;


@SpringBootTest
public class TeamEndointsTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private TeamRepository teamRepository;
	
	private MockWebServiceClient mockWebServiceClient;
	
	@BeforeEach
	public void createClient() throws Exception {
		mockWebServiceClient = MockWebServiceClient.createClient(applicationContext);
	}
	
	@Test
	public void testGetTeam() throws Exception{
		Source requestPayload = new StringSource(
				"<com:getTeamRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:tid>1</com:tid>"
						+ "</com:getTeamRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:getTeamResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:teamSoap>"
						+ "		<ns2:tId>1</ns2:tId>"
						+ "		<ns2:teamSoapInfo>"
						+ "			<ns2:tId>1</ns2:tId>"
						+ "			<ns2:name>Paris Saint Germain</ns2:name>"
						+ "			<ns2:country>France</ns2:country>"
						+ "			<ns2:type>Club</ns2:type>"
						+ "			<ns2:captain>Marquinhos</ns2:captain>"
						+ "		</ns2:teamSoapInfo>"
						+ "		<ns2:players>"
						+ " 		<ns2:playerSoapInfo>"
						+ "				<ns2:pid>1</ns2:pid>"
						+ "				<ns2:name>Neymar Jr</ns2:name>"
						+ "				<ns2:age>28</ns2:age>"
						+ "				<ns2:citizenship>Brazillian</ns2:citizenship>"
						+ "			</ns2:playerSoapInfo>"
						+ "			<ns2:position>Ailier gauche</ns2:position>"
						+ "		</ns2:players>"
						+ "	</ns2:teamSoap>"
						+ "</ns2:getTeamResponse>");
		
		mockWebServiceClient
			.sendRequest(withPayload(requestPayload))
			.andExpect(payload(expectedResponsePayload));
	}
	
	@Test
	public void testCreateTeam() throws Exception{
		Source requestPayload = new StringSource(
				"<com:createTeamRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:teamSoap>"
						+ "		<com:tid>0</com:tid>"
						+ "		<com:teamSoapInfo>"
						+ "			<com:tId>0</com:tId>"
						+ "			<com:name>Seleção</com:name>"
						+ "			<com:country>Brazil</com:country>"
						+ "			<com:type>National team</com:type>"
						+ "			<com:captain>Casemiro</com:captain>"
						+ "		</com:teamSoapInfo>"
						+ "			<com:players>"
						+ " 			<com:playerSoapInfo>"
						+ "					<com:pid>1</com:pid>"
						+ "					<com:name>Neymar Jr</com:name>"
						+ "					<com:age>28</com:age>"
						+ "					<com:citizenship>Brazillian</com:citizenship>"
						+ "				</com:playerSoapInfo>"
						+ "				<com:position>Ailier gauche</com:position>"
						+ "			</com:players>"
						+ "		</com:teamSoap>"
						+ "</com:createTeamRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:createTeamResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:teamSoap>"
						+ "		<ns2:tId>3</ns2:tId>"
						+ "		<ns2:teamSoapInfo>"
						+ "			<ns2:tId>3</ns2:tId>"
						+ "			<ns2:name>Seleção</ns2:name>"
						+ "			<ns2:country>Brazil</ns2:country>"
						+ "			<ns2:type>National team</ns2:type>"
						+ "			<ns2:captain>Casemiro</ns2:captain>"
						+ "		</ns2:teamSoapInfo>"
						+ "		<ns2:players>"
						+ " 		<ns2:playerSoapInfo>"
						+ "				<ns2:pid>1</ns2:pid>"
						+ "				<ns2:name>Neymar Jr</ns2:name>"
						+ "				<ns2:age>28</ns2:age>"
						+ "				<ns2:citizenship>Brazillian</ns2:citizenship>"
						+ "			</ns2:playerSoapInfo>"
						+ "			<ns2:position>Ailier gauche</ns2:position>"
						+ "		</ns2:players>"
						+ "	</ns2:teamSoap>"
						+ "</ns2:createTeamResponse>");
		
		mockWebServiceClient
		.sendRequest(withPayload(requestPayload))
		.andExpect(payload(expectedResponsePayload));
		
		TeamEntity team = this.teamRepository.findById(Long.valueOf(3)).orElseThrow(() -> new TeamNotFoundException(Long.valueOf(3)));
		this.teamRepository.delete(team);
		
	}
	
	@Test
	public void testModifyTeam() throws Exception{
		
		Source requestPayload = new StringSource(
				"<com:modifyTeamRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:teamSoap>"
						+ "		<com:tId>1</com:tId>"
						+ "		<com:teamSoapInfo>"
						+ "			<com:tId>1</com:tId>"
						+ "			<com:name>Les aigles volants</com:name>"
						+ "			<com:country>Brazil</com:country>"
						+ "			<com:type>National team</com:type>"
						+ "			<com:captain>Mbappe</com:captain>"
						+ "		</com:teamSoapInfo>"
						+ "			<com:players>"
						+ " 			<com:playerSoapInfo>"
						+ "					<com:pid>1</com:pid>"
						+ "					<com:name>Neymar Jr</com:name>"
						+ "					<com:age>28</com:age>"
						+ "					<com:citizenship>Brazillian</com:citizenship>"
						+ "				</com:playerSoapInfo>"
						+ "				<com:position>Milieu</com:position>"
						+ "			</com:players>"
						+ "		</com:teamSoap>"
						+ "</com:modifyTeamRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:modifyTeamResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:teamSoap>"
						+ "		<ns2:tId>1</ns2:tId>"
						+ "		<ns2:teamSoapInfo>"
						+ "			<ns2:tId>1</ns2:tId>"
						+ "			<ns2:name>Les aigles volants</ns2:name>"
						+ "			<ns2:country>Brazil</ns2:country>"
						+ "			<ns2:type>National team</ns2:type>"
						+ "			<ns2:captain>Mbappe</ns2:captain>"
						+ "		</ns2:teamSoapInfo>"
						+ "		<ns2:players>"
						+ " 		<ns2:playerSoapInfo>"
						+ "				<ns2:pid>1</ns2:pid>"
						+ "				<ns2:name>Neymar Jr</ns2:name>"
						+ "				<ns2:age>28</ns2:age>"
						+ "				<ns2:citizenship>Brazillian</ns2:citizenship>"
						+ "			</ns2:playerSoapInfo>"
						+ "			<ns2:position>Milieu</ns2:position>"
						+ "		</ns2:players>"
						+ "	</ns2:teamSoap>"
						+ "</ns2:modifyTeamResponse>");
		
		mockWebServiceClient
		.sendRequest(withPayload(requestPayload))
		.andExpect(payload(expectedResponsePayload));
	}
	
	@Test
	public void testDeleteTeam() throws Exception{
		Source requestPayload = new StringSource(
				"<com:deleteTeamRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:tId>2</com:tId>"
						+ "</com:deleteTeamRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:deleteTeamResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:deleteResult>Deletion succesful</ns2:deleteResult>"
						+ "</ns2:deleteTeamResponse>");
		
		mockWebServiceClient
			.sendRequest(withPayload(requestPayload))
			.andExpect(payload(expectedResponsePayload));
	}
	
}
