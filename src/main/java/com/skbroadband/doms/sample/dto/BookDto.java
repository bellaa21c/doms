package com.skbroadband.doms.sample.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.sample.dto
 * @File : BookDto
 * @Program :
 * @Date : 2022-12-12
 * @Comment :
 */
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookDto implements Serializable {
    private Long id;

    @NotBlank(message = "{required}")
    private String title;
    @NotNull(message = "작가번호를 입력하세요.")
    private Long author_id;
    private String firstName;
    private String lastName;

//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class RequestDto implements Serializable {
//        private Long id;
//        private String title;
//        private Long author_id;
//        private String firstName;
//        private String lastName;
//
//        public Book toEntity() {
//            return Book.builder()
//                    .title(title)
//                    .author(Author.builder()
//                            .id(author_id)
//                            .firstName(firstName)
//                            .lastName(lastName)
//                            .build())
//                    .build();
//        }
//    }

//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class Response implements Serializable {
//        private Long id;
//        private String title;
//        private String firstName;
//        private String lastName;
//
//        public Response(Book book) {
//            this.id = book.getId();
//            this.title = book.getTitle();
//            this.firstName = book.getAuthor().getFirstName();
//            this.lastName = book.getAuthor().getLastName();
//        }
//    }
}
