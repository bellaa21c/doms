package com.skbroadband.doms.global.config;

import com.skbroadband.doms.global.component.crypto.Crypto;
import com.skbroadband.doms.global.component.crypto.JceCryptoComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : CryptoConfig
 * @Program :
 * @Date : 2022-12-16
 * @Comment :
 */
@Configuration
public class CryptoConfig {
    @Bean
    public Crypto crypto(@Value("${application.crypto.password}") String password) {
        return new JceCryptoComponent(password);
    }
}
