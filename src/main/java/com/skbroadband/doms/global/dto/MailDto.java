package com.skbroadband.doms.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.dto
 * @File : MailDto
 * @Program :
 * @Date : 2022-12-14
 * @Comment :
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailDto {
    private String address;
    private String title;
    private String message;
}
