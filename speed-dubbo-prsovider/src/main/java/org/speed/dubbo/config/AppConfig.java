package org.speed.dubbo.config;

import javax.servlet.http.HttpServlet;

import org.apache.dubbo.remoting.http.servlet.DispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"spring-bean.xml"})
public class AppConfig {
	
	@Bean
	public ServletRegistrationBean<HttpServlet> dubboDispatchServlet() {
		ServletRegistrationBean<HttpServlet> srgb = new ServletRegistrationBean<HttpServlet>(new DispatcherServlet(), "/dubboServices/*");
		srgb.setName("dubboDispatchServlet");
		return srgb;
	}
	
}
