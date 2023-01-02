package com.skbroadband.doms.global.config;

import com.skbroadband.doms.global.component.thyemeleaf.CustomHrefDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : ThyemeleafConfig
 * @Program :
 * @Date : 2022-12-20
 * @Comment :
 */
@Configuration
public class ThymeleafConfig {
    @Bean
    public CustomHrefDialect linkDialect() {
        return new CustomHrefDialect("UTF-8");
    }
}
