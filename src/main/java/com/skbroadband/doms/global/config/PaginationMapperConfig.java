package com.skbroadband.doms.global.config;

import org.mapstruct.MapperConfig;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : MapperConfig
 * @Program :
 * @Date : 2022-12-16
 * @Comment :
 */
@MapperConfig(componentModel = "spring", uses = PaginationMapper.class)
public interface PaginationMapperConfig {
}

