package org.speed.dubbo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.ProxyFactory;

public class SPIMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ProxyFactory PROXY_FACTORY = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
		System.out.println(PROXY_FACTORY.toString());
	}

}
