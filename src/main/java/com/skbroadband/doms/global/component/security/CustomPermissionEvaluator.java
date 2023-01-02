package com.skbroadband.doms.global.component.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.component.security
 * @File : CustomPermissionEvaluator
 * @Program :
 * @Date : 2022-12-28
 * @Comment :
 */
@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.debug("ACL Permission ");

//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(QRoleDto.roleDto.roleId.in(authentication.getAuthorities().stream()
//                //.filter(simpleGrantedAuthority-> !simpleGrantedAuthority.getAuthority().equals(""))
//                .findAny().get().getAuthority()
//        ));
//
//        long permissionCount = roleJpaRepository.count(builder);
//        if(permissionCount >= 1) {
//            return true;
//        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
