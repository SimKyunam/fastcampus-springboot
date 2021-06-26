package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // -------------------전 처리-------------------
        //ContentCachingRequestWrapper, ContentCachingResponseWrapper
        //캐싱을 함으로써, chain.doFilter 값을 사용해도 결과 값으로 출력이 가능하다.
        //캐싱하지 않으면 chain.doFilter 후 request, response 값이 없다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);
        // -------------------전 처리-------------------

        chain.doFilter(httpServletRequest, httpServletResponse);

        // -------------------후 처리-------------------
        //request info log 표시
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url: {}, reqContent: {}", url, reqContent);

        //response info log 표시
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
        httpServletResponse.copyBodyToResponse(); //이 메소드를 안쓰면 response 값이 찍히지 않는다.
        log.info("response status: {}, responseBody: {}", httpStatus, resContent);
        // -------------------후 처리-------------------
    }
}
