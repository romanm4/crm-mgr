package com.crm.mgr.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class CustomRequestLoggingFilter extends AbstractRequestLoggingFilter {
    private static final Logger log = LoggerFactory.getLogger(CustomRequestLoggingFilter.class);
    private static final String X_TRACE_ID = "X-Trace-Id";

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(req, resp);
        logRequest(request, req);
        logResponse(request, response, resp);
        resp.copyBodyToResponse();
    }

    private void logRequest(HttpServletRequest httpServletRequest, ContentCachingRequestWrapper requestWrapper) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        stringBuilder.append("HTTP Request [--->] ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        byte[] requestBody = requestWrapper.getContentAsByteArray();
        stringBuilder.append("body=[").append(new String(requestBody, StandardCharsets.UTF_8)).append("]");

        log.info(stringBuilder.toString());
    }

    private void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ContentCachingResponseWrapper responseWrapper) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("HTTP Response [<---] ");
        stringBuilder.append("status=[").append(httpServletResponse.getStatus()).append("] ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");

        byte[] responseBody = responseWrapper.getContentAsByteArray();
        stringBuilder.append("responseBody=[").append(new String(responseBody, StandardCharsets.UTF_8)).append("] ");

        log.info(stringBuilder.toString());
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        return map;
    }

    private void setXTraceId(HttpServletRequest request, HttpServletResponse response) {
        String xTraceId = request.getHeader(X_TRACE_ID);
        if (Objects.isNull(xTraceId)) {
            xTraceId = UUID.randomUUID().toString();
        }
        MDC.put(X_TRACE_ID, xTraceId);
        response.setHeader(X_TRACE_ID, xTraceId);
    }
}
