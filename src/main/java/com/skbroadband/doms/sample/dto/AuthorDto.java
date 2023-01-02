package com.skbroadband.doms.sample.dto;

import com.skbroadband.doms.sample.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link Author} entity
 */
@Getter
@AllArgsConstructor
public class AuthorDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Set<BookDto> books;
}