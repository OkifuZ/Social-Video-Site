package com.db2020prj.jwt;

import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper wrapper = null;

        System.out.println("request filtered. request: " + request.toString() + " response: " + response.toString());
        if (request instanceof HttpServletRequest) {
            try {
                wrapper = new RequestWrapper((HttpServletRequest) request);
                System.out.println("filter success. target: " + wrapper.getRequestURL());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        chain.doFilter(null == wrapper ? request : wrapper, response);
    }
}
