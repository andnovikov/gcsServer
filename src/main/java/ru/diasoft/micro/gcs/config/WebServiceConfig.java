package ru.diasoft.micro.gcs.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "inter-gate-ibank-ws")
	public Wsdl11Definition defaultWsdl11Definition(){
		SimpleWsdl11Definition wsdl11Definition  = new SimpleWsdl11Definition();
		wsdl11Definition .setWsdl(new ClassPathResource("wsdl/inter-gate-ibank-ws.wsdl"));
		return wsdl11Definition ;
	}

	@Bean(name = "types")
	public SimpleXsdSchema simpleXsdSchema(){
		return new SimpleXsdSchema(new ClassPathResource("wsdl/types.xsd"));
	}
}
