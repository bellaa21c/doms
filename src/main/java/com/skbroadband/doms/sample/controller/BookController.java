package com.skbroadband.doms.sample.controller;

import com.skbroadband.doms.sample.dto.BookDto;
import com.skbroadband.doms.sample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.sample.controller
 * @File : BookController
 * @Program :
 * @Date : 2022-12-19
 * @Comment :
 */
@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping(value = "/sample/books", produces = MediaType.TEXT_HTML_VALUE)
    public String books(String title,
                        String firstName,
                        String lastName, Pageable pageable,
                        Model model){
        model.addAttribute("books", bookService.findBooks(title, firstName, lastName, pageable));

        return "sample/list";
    }

    @GetMapping(value = "/sample/book/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String book(@PathVariable Long id, Model model){
        model.addAttribute("book",  bookService.findBook(id));

        return "sample/detail";
    }

    @GetMapping(value = "/sample/book/add", produces = MediaType.TEXT_HTML_VALUE)
    public String book(Model model){
        model.addAttribute("book",  BookDto.builder().build());

        return "sample/detail";
    }

    @PostMapping(value = "/sample/book", produces = MediaType.TEXT_HTML_VALUE)
    public String bookAdd(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {
//        throw new BadRequestException("test");
        if (bindingResult.hasErrors()) {
//            if(bindingResult.hasFieldErrors("title")) {
//                bindingResult.rejectValue("title", "required", new String[]{"타이틀"}, null);
//            }

            return "sample/detail";
        }
        bookService.addBook(bookDto);

        return "redirect:sample/list";
    }
}
