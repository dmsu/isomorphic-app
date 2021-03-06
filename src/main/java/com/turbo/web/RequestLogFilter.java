package com.turbo.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger("requestLogger");
    private String nodeId = Integer.toHexString((int)(Math.random() * 256)) + "-";
    private AtomicInteger requestId = new AtomicInteger(0);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        try {
            Thread.currentThread().setName(nodeId + requestId.incrementAndGet());
            logger.info(request.getRemoteAddr() + " " + http.getMethod() + " " + http.getRequestURI() + " " + serializeParams(request));
            filterChain.doFilter(request, response);
        }
        catch (Throwable e) {
            logger.error("", e);
        }
    }

    private String serializeParams(ServletRequest request) {
        StringBuilder builder = new StringBuilder(256);
        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            builder.append(param.getKey()).append("=").append(normalize(param.getValue())).append("\t");
        }
        return builder.toString();
    }

    private String normalize(String[] value) {
        String result = value.length == 1 ? value[0] : Arrays.toString(value);
        return result.replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t");
    }

    public void destroy() {
    }
}

