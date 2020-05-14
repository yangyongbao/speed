package org.speed.resilience4j;

import org.junit.Test;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

/**
 * Unit test for simple App.
 */
public class AppTest {

	
	@Test
    public void testCircuitBreaker(){
		
		CircuitBreakerRegistry circuitBreakerRegistry = null;
		
		
		
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
                .custom()
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker
                .of("backendName",circuitBreakerConfig);
        
        
        String result = circuitBreaker.executeSupplier(() -> {
        	System.out.println("ok");
        	return "ok";
        });
        
        System.out.println(result);
    }
	
	
}
