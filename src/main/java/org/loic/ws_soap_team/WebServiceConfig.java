package org.loic.ws_soap_team;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

	@Bean
	public XsdSchema teamsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsdFiles/teams.xsd"));
	}
	
	@Bean
	public XsdSchema playersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsdFiles/players.xsd"));
	}
	
	@Bean(name = "teams")
	public DefaultWsdl11Definition defaultTeamsWsdl11Definition(XsdSchema teamsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("TeamsPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://loic.org/ws/components");
		wsdl11Definition.setSchema(teamsSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "players")
	public DefaultWsdl11Definition defaultPlayersWsdl11Definition(XsdSchema playersSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PlayersPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://loic.org/ws/components");
		wsdl11Definition.setSchema(playersSchema);
		System.out.print(playersSchema + "\n");
		return wsdl11Definition;
	}
	
	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<Servlet>(servlet, "/ws/*");
	}
}
