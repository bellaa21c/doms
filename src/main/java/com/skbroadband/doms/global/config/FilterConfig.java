package com.skbroadband.doms.global.config;

//import com.skbroadband.doms.global.component.ReadableRequestWrapperFilter;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : FilterConfig
 * @Program :
 * @Date : 2022-11-23
 * @Comment :
 */
@Configuration
public class FilterConfig {
    /**
     * request, response 로깅처리를 위한 필터
     * @return
     */
//    @Bean
//    public FilterRegistrationBean<ReadableRequestWrapperFilter> readableRequestWrapperFilter() {
//        FilterRegistrationBean<ReadableRequestWrapperFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new ReadableRequestWrapperFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setName("readableRequestWrapperFilter");
//
//        return filterRegistrationBean;
//    }

    /**
     * xss filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> xssEscapeServletFilter() {
        FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new XssEscapeServletFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("xssEscapeServletFilter");

        return filterRegistrationBean;
    }
}
