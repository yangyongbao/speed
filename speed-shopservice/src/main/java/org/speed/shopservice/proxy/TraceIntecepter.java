package org.speed.shopservice.proxy;

import java.util.concurrent.atomic.AtomicInteger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TraceIntecepter implements MethodInterceptor {
	
	final ThreadLocal<AtomicInteger> GenId = new ThreadLocal<AtomicInteger>() {
		@Override
		protected AtomicInteger initialValue() {
			return new AtomicInteger(0);
		}
	};
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		Object result = null;
		try {
			TraceAgent.startTrace(invocation);
			result = invocation.proceed();
			TraceAgent.outTrace();
		}finally {
			TraceAgent.stopTrace();
		}
		
		return result;
	}
}
