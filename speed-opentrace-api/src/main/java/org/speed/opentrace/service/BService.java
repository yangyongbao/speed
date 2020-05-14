package org.speed.opentrace.service;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.CurrentTraceContext;

public class BService {

	public void action() {
		Tracing tracing = Tracing.current();
		Tracer tracer = tracing.tracer();
		CurrentTraceContext ctx = tracing.currentTraceContext();
		Span span = tracer.newChild(ctx.get()).name("BService").start();
		System.out.println("BService.action");
		
		span.finish();

	}
	
}
