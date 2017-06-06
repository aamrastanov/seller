package az.tezapp.seller.server.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class WebRequestTraceFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(WebRequestTraceFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        RequestWrapper requestWrapper = new RequestWrapper(request);
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("REQUEST: " + getRequestTrace(request));
            this.logger.trace("REQUEST BODY: " + requestWrapper.getBody());
        }
        try {
            filterChain.doFilter(requestWrapper, response);
        } finally {
        }
    }

    private Map<String, Object> getRequestTrace(HttpServletRequest request) {

        Map<String, Object> headers = new LinkedHashMap<String, Object>();
        Enumeration<String> names = request.getHeaderNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            List<String> values = Collections.list(request.getHeaders(name));
            Object value;
            if (values.isEmpty()) {
                value = "";
            } else if (values.size() == 1) {
                value = values.get(0);
            } else {
                value = values;
            }
            headers.put(name, value);

        }

        Map<String, Object> trace = new LinkedHashMap<String, Object>();
        trace.put("remoteAddress", request.getRemoteHost() + ":" + request.getRemotePort());
        trace.put("remoteUser", request.getRemoteUser());
        trace.put("method", request.getMethod());
        trace.put("path", request.getRequestURI());
        trace.put("headers", headers);
        return trace;
    }

}
