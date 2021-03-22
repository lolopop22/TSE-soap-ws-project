package org.loic.api_soap_team;

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
		Source requestPayload = new StringSource("<getPlayerRequest xmlns='http://loic.org/ws/components'>"
				+ "<pid>1</pid>" + "</getPlayerRequest>");
		
		Source expectedResponsePayload = new StringSource(
				"<getPlayerResponse xmlns='http://loic.org/ws/components'>"
						+ "	<playerSoap>"
						+ "		<pid>1</pid>"
						+ " 	<playerSoapInfo>"
						+ "			<pid>1</pid>"
						+ "			<name>Neymar Jr</name>"
						+ "			<age>28</age>"
						+ "			<citizenship>Brazillian</citizenship>"
						+ "		</playerSoapInfo>"
						+ "		<teams>"
						+ "			<teamSoapInfo>"
						+ "				<tId>1<tId>"
						+ "				<name>Paris Saint Germain</name>"
						+ "				<country>France</country>"
						+ "				<type>club</club>"
						+ "				<captain>Marquinhos</captain>"
						+ "			</teamSoapInfo>"
						+ "			<position>Ailier gauche</position>"
						+ "		</teams>"
						+ "	</playerSoap>"
						+ "</getPlayerResponse>");
		
		mockWebServiceClient
			.sendRequest(withPayload(requestPayload))
			.andExpect(payload(expectedResponsePayload));
	}
	
}
