package org.speed.dubbo;

import java.math.BigDecimal;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.ProxyFactory;
import org.speed.dubbo.config.AppConfig;
import org.speed.dubbo.model.Order;
import org.speed.dubbo.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		OrderService orderService = ctx.getBean("orderService", OrderService.class);
		
		String traceId = "traceId-111111";
		Order order = new Order();
		order.setOrderId("1");
		order.setPrice(BigDecimal.ONE);
		orderService.order(order , traceId);**/
		
		
		//Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();

		//ProxyFactory PROXY_FACTORY = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
		
		
		URL url = new URL("hessian://192.168.99.1:8081/dubboServices/org.speed.dubbo.service.OrderService?anyhost=true&application=speed-dubbo-provider&bean.name=org.speed.dubbo.service.OrderService&bind.ip=192.168.99.1&bind.port=8081&delay=-1&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=org.speed.dubbo.service.OrderService&methods=order&pid=55550&register=true&release=2.7.3&server=servlet&side=provider&timestamp=1588786795463", null, 0);
        String extName = url.getParameter("proxy", "javassist");
		System.out.println(extName);


		//Invoker invoker = null;
		//protocol.export(invoker );
		
		System.out.println("ok");
		
	}

}
