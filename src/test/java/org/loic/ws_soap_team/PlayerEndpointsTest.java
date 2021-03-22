package org.loic.ws_soap_team;

import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

import javax.xml.transform.Source;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

@SpringBootTest
public class PlayerEndpointsTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private MockWebServiceClient mockWebServiceClient;
	
	@BeforeEach
	public void createClient() throws Exception {
		mockWebServiceClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void testGetPlayer() throws Exception{
		Source requestPayload = new StringSource(
				"<com:getPlayerRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:pid>1</com:pid>"
						+ "</com:getPlayerRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:getPlayerResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:playerSoap>"
						+ "		<ns2:pid>1</ns2:pid>"
						+ " 	<ns2:playerSoapInfo>"
						+ "			<ns2:pid>1</ns2:pid>"
						+ "			<ns2:name>Neymar Jr</ns2:name>"
						+ "			<ns2:age>28</ns2:age>"
						+ "			<ns2:citizenship>Brazillian</ns2:citizenship>"
						+ "		</ns2:playerSoapInfo>"
						+ "		<ns2:teams>"
						+ "			<ns2:teamSoapInfo>"
						+ "				<ns2:tId>1</ns2:tId>"
						+ "				<ns2:name>Paris Saint Germain</ns2:name>"
						+ "				<ns2:country>France</ns2:country>"
						+ "				<ns2:type>Club</ns2:type>"
						+ "				<ns2:captain>Marquinhos</ns2:captain>"
						+ "			</ns2:teamSoapInfo>"
						+ "			<ns2:position>Ailier gauche</ns2:position>"
						+ "		</ns2:teams>"
						+ "	</ns2:playerSoap>"
						+ "</ns2:getPlayerResponse>");
		
		mockWebServiceClient
			.sendRequest(withPayload(requestPayload))
			.andExpect(payload(expectedResponsePayload));
	}
	
	@Test
	public void testCreatePlayer() throws Exception{
		
		Source requestPayload = new StringSource(
				"<com:createPlayerRequest xmlns:com='http://loic.org/ws/components'>"
				+ "	<com:playerSoapInfo>"
				+ "		<com:pid>0</com:pid>"
				+ "		<com:name>Casemiro</com:name>"
				+ "		<com:age>37</com:age>"
				+ "		<com:citizenship>Brazillian</com:citizenship>"
				+ "	</com:playerSoapInfo>"
				+ "</com:createPlayerRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:createPlayerResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:playerSoap>"
						+ "		<ns2:pid>3</ns2:pid>"
						+ " 	<ns2:playerSoapInfo>"
						+ "			<ns2:pid>3</ns2:pid>"
						+ "			<ns2:name>Casemiro</ns2:name>"
						+ "			<ns2:age>37</ns2:age>"
						+ "			<ns2:citizenship>Brazillian</ns2:citizenship>"
						+ "		</ns2:playerSoapInfo>"
						+ "	</ns2:playerSoap>"
						+ "</ns2:createPlayerResponse>");
		
		mockWebServiceClient
			.sendRequest(withPayload(requestPayload))
			.andExpect(payload(expectedResponsePayload));
		
	}
	
	@Test
	public void testModifyPlayer() throws Exception{
		
		Source requestPayload = new StringSource(
				"<com:modifyPlayerRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:playerSoapInfo>"
						+ "		<com:pid>3</com:pid>"
						+ "		<com:name>Casemiro</com:name>"
						+ "		<com:age>50</com:age>"
						+ "		<com:citizenship>Ukrainian</com:citizenship>"
						+ "	</com:playerSoapInfo>"
						+ "</com:modifyPlayerRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:modifyPlayerResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:playerSoap>"
						+ "		<ns2:pid>3</ns2:pid>"
						+ " 	<ns2:playerSoapInfo>"
						+ "			<ns2:pid>3</ns2:pid>"
						+ "			<ns2:name>Casemiro</ns2:name>"
						+ "			<ns2:age>50</ns2:age>"
						+ "			<ns2:citizenship>Ukrainian</ns2:citizenship>"
						+ "		</ns2:playerSoapInfo>"
						+ "	</ns2:playerSoap>"
						+ "</ns2:modifyPlayerResponse>");
		
		mockWebServiceClient
		.sendRequest(withPayload(requestPayload))
		.andExpect(payload(expectedResponsePayload));
		
	}
	
	@Test
	public void testDeletePlayer() throws Exception{
		Source requestPayload = new StringSource(
				"<com:deletePlayerRequest xmlns:com='http://loic.org/ws/components'>"
						+ "	<com:pid>2</com:pid>"
						+ "</com:deletePlayerRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<ns2:deletePlayerResponse xmlns:ns2='http://loic.org/ws/components'>"
						+ "	<ns2:deleteResult>Deletion succesful</ns2:deleteResult>"
						+ "</ns2:deletePlayerResponse>");
		
		mockWebServiceClient
			.sendRequest(withPayload(requestPayload))
			.andExpect(payload(expectedResponsePayload));
	}
	
}
