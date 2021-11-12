package com.spring.demo.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogApiFilter extends OncePerRequestFilter {

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                     FilterChain chain)throws ServletException,IOException{
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        chain.doFilter(request,response);
        logAPI(request,response);
        logBody(requestWrapper,responseWrapper);

        responseWrapper.copyBodyToResponse();

    }

    private  void logAPI(HttpServletRequest request, HttpServletResponse response){

        int httpStatus = response.getStatus();
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI();
        String params = request.getQueryString();

        if (params != null){
            uri +="?"+params;
        }

        System.out.println(String.join(" ",String.valueOf(httpStatus),httpMethod,uri));
    }

    private void logBody (ContentCachingRequestWrapper request ,ContentCachingResponseWrapper response){
        String requestBody = getContet(request.getContentAsByteArray());
        System.out.println("Request: "+requestBody);

        String responseBody = getContet(response.getContentAsByteArray());
        System.out.println("Response: "+responseBody);
    }

    private String getContet (byte[] content){
        String body = new String(content);
        return body.replaceAll("[\n\t]]","");
    }
}
