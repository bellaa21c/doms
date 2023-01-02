package com.skbroadband.doms.sample.mapper;

import com.skbroadband.doms.sample.dto.BookDto;
import com.skbroadband.doms.sample.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.sample.mapper
 * @File : BookMapper
 * @Program :
 * @Date : 2022-12-16
 * @Comment :
 */
@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "book.author.id", target = "author_id")
    @Mapping(source = "book.author.firstName", target = "firstName")
    @Mapping(source = "book.author.lastName", target = "lastName")
    BookDto toBookDto(Book book);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "author_id", target = "author.id")
    Book toEntity(BookDto bookDto);

    List<BookDto> toDtoList(List<Book> books);
}
