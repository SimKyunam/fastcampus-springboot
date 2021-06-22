package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        //HttpServletRequest를 이용하면 요청한 라인을 모두 읽어서, 스프링에서 쓸 수 있는 값이 없어 에러남.
        //HttpServletRequest -> ContentCachingRequestWrapper 변경하면 그런 문제 해결
        //그러나 생성자에선 값을 복사한게 아니라 길이만 복사하여 에러가 발생
//        BufferedReader br = httpServletRequest.getReader();
//        br.lines().forEach(line -> {
//            log.info("url : {}, line: {}", url, line);
//        });

        chain.doFilter(httpServletRequest, httpServletResponse);

        String url = httpServletRequest.getRequestURI();

        // 후 처리
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("response url: {}, reqContent: {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();

        log.info("response status: {}, responseBody: {}", httpStatus, resContent);
    }
}
