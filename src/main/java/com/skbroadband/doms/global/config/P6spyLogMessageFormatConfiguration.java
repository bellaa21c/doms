package com.skbroadband.doms.global.config;

import com.p6spy.engine.spy.P6SpyOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : P6spyLogMessageFormatConfiguration
 * @Program :
 * @Date : 2022-12-28
 * @Comment :
 */
@Profile(value = {"sample", "local"})
@Configuration
public class P6spyLogMessageFormatConfiguration {
    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spySqlFormatConfiguration.class.getName());
    }
}
