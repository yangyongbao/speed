package org.speed.opentrace;

import org.speed.opentrace.service.AService;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;
import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	AService aService = new AService();
    	
    	Tracing tracing = Tracing.newBuilder().localServiceName("test")
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .build();
        Tracer tracer = tracing.tracer();
        Span root = tracer.nextSpan().name("root").start();
        SpanInScope scope = null;
        try {
            scope = tracer.withSpanInScope(root);
            Span s1 = tracer.newChild(tracing.currentTraceContext().get()).name("s1").start();
            
            aService.action();            
            
            s1.finish();
        } catch (Exception e) {
            root.error(e);
        } finally {
            scope.close();//结束作用域
        }
        root.finish();//结束跨度 root
        
        
    	System.out.println( "Hello World!" );
    }
}
