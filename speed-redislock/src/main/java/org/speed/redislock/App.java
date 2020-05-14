package org.speed.redislock;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Redisson redisson = null;
    	
    	
    	String key = "sync_order_id_" + "110000111";
    	RLock lock = redisson.getLock(key);
    	try {
    		lock.tryLock(30, 30, TimeUnit.SECONDS);
    		
    		
    	} catch (Exception e) {
    		
    	}finally {
    		lock.unlock();
		}
    	
    	
        System.out.println( "Hello World!" );
    }
}
