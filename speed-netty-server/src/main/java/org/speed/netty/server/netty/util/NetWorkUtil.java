package org.speed.netty.server.netty.util;

import java.net.InetAddress;

public class NetWorkUtil {
	
	
	public static String localIp() {
		String localIp = "";
		
		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
		}catch (Exception e) {
		}
		
		return localIp;
	}

	
	
}
