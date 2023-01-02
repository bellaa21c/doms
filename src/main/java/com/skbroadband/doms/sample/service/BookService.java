package com.skbroadband.doms.sample.service;

import com.skbroadband.doms.global.exception.NotFoundException;
import com.skbroadband.doms.sample.dto.AuthorDto;
import com.skbroadband.doms.sample.dto.BookDto;
import com.skbroadband.doms.sample.mapper.BookMapper;
import com.skbroadband.doms.sample.repository.AuthorRepository;
import com.skbroadband.doms.sample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.example.service
 * @File : ExampleService
 * @Program :
 * @Date : 2022-11-17
 * @Comment :
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Page<BookDto> findBooks(String title,
                                   String firstName,
                                   String lastName, Pageable pageable) {
        return bookRepository.findAll(title, firstName, lastName, pageable)
                .map(bookMapper::toBookDto);
    }

    @Transactional(readOnly = true)
    public BookDto findBook(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDto)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public BookDto addBook(BookDto bookDto) {
        return bookMapper.toBookDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    @Transactional
    public void saveAuthor(AuthorDto authorDto) {
        //authorRepository.save(bookMapper.toEntity(authorDto));
    }
}
