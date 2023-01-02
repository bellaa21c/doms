package com.skbroadband.doms.global.component.log;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.component
 * @File : HttpMessageWrapperFilter
 * @Program :
 * @Date : 2022-12-28
 * @Comment :
 */
public class HttpMessageWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(new CachingRequestWrapper(request), new CachingResponseWrapper(response)
            );
        }
    }
}
