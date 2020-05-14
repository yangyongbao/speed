package org.speed.opentrace.service;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.CurrentTraceContext;

public class AService {
	
	
	BService bService;
	
	
	public AService(){
		bService = new BService();
	}
	
	public void action() {
		
		Tracing tracing = Tracing.current();
		Tracer tracer = tracing.tracer();
		CurrentTraceContext ctx =  Tracing.current().currentTraceContext();
		
		String sId = ctx.get().spanIdString();
		System.out.println("sId=" + sId );

		
		Span span = tracer.newChild(ctx.get()).name("AService").start();
		span.tag("v1", "12");
		span.tag("v2", "22");
		span.tag("v3", "33");
		
		
		System.out.println("AService.action");
		
		bService.action();
		
		
		span.finish();
	}


}
