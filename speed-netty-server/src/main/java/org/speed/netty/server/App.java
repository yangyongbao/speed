package org.speed.netty.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {	
    	
    	System.setProperty("spring.main.web-application-type", "NONE");
    	SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
    }
}
