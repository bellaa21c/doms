package com.skbroadband.doms.sample.controller;

import com.skbroadband.doms.global.dto.Response;
import com.skbroadband.doms.sample.dto.BookDto;
import com.skbroadband.doms.sample.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.example.controller
 * @File : BookRestController
 * @Program :
 * @Date : 2022-11-17
 * @Comment :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping(value = "/sample/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> books(String title,
                                   String firstName,
                                   String lastName, Pageable pageable) {
        Page<BookDto> books = bookService.findBooks(title, firstName, lastName, pageable);

        return Response.of(books);
    }

    @GetMapping(value = "/sample/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> book(@PathVariable Long id) {
        BookDto bookDto = bookService.findBook(id);

        return Response.of(bookDto);
    }

    @PostMapping(value = "/sample/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bookAdd(@Valid @RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);

        return Response.ok();
    }
}
