package com.skbroadband.doms.sample.repository;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.example.repository
 * @File : ExampleCustomRepository
 * @Program :
 * @Date : 2022-11-18
 * @Comment :
 */

import com.skbroadband.doms.sample.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface BookSupportRepository {
    Page<Book> findAll(String title, String firstName, String lastName, Pageable pageable);
}
