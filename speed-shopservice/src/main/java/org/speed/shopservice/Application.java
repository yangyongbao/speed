package org.speed.shopservice;

import org.speed.shopservice.proxy.TraceIntecepter;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class Application 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class,args);
    }
    
    @Bean
	public TraceIntecepter traceIntecepter(){
		return new TraceIntecepter();
	}
	
	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator=new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setBeanNames("user*","shop*","$Proxy*");
		beanNameAutoProxyCreator.setInterceptorNames("traceIntecepter");
		return beanNameAutoProxyCreator;
	}
    
}
