package com.skbroadband.doms.sample.repository;

import com.skbroadband.doms.sample.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.sample.repository
 * @File : AuthorRepository
 * @Program :
 * @Date : 2022-12-14
 * @Comment :
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
