package com.skbroadband.doms.global.component.crypto;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.component
 * @File : Crypto
 * @Program :
 * @Date : 2022-12-16
 * @Comment :
 */
public interface Crypto {
    String enc(String text) throws Exception;
    String dec(String text) throws Exception;
}
