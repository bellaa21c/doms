package com.skbroadband.doms.global.component.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.component.log
 * @File : HttpMessageLogInterceptor
 * @Program :
 * @Date : 2022-12-28
 * @Comment :
 */
//@Component
@Slf4j
@RequiredArgsConstructor
public class HttpMessageLogInterceptor extends HandlerInterceptorAdapter {
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request instanceof CachingRequestWrapper) {
            String req = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            log.info("request - {}", req);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        if (response instanceof CachingResponseWrapper) {
            String res = IOUtils.toString(((CachingResponseWrapper) response).getContentInputStream(), response.getCharacterEncoding());
            log.info("response - {}", res);
        }
    }
}
