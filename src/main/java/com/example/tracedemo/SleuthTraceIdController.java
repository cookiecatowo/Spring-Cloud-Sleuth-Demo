package com.example.tracedemo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import brave.Span;
import brave.Tracer;
import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@RestController
public class SleuthTraceIdController {

    private static final Logger logger = LoggerFactory.getLogger(SleuthTraceIdController.class);

    @Autowired
    private Tracer tracer;

    @GetMapping("/")
    public String home(){
        logger.info("Hello with Sleuth");
        return "Hello from Sleuth";
    }

    @GetMapping("/traceid")
    public String getSleuthTraceId() {
        Span span = tracer.currentSpan();
        if (span != null){
            logger.info("Trace ID {}", span.context().traceIdString());
            logger.info("Span ID {}", span.context().spanIdString());
        }
        return span.context().traceIdString();
    }
}