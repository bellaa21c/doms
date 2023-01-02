package com.skbroadband.doms.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.web.service
 * @File : UserService
 * @Program :
 * @Date : 2022-12-08
 * @Comment :
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final UserRepository userRepository;

    @Transactional
    public void insert() {
        //FIXME: dto를 받아서 저장
        //bCrytPasswordEncoder.encode()
        //userRepository.save();
    }
}
