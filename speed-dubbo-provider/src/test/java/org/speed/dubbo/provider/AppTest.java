package org.speed.dubbo.provider;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
	public void testA() {
		
		Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
		  
		System.out.println(protocol);
	}
	
}
