package com.skbroadband.doms.sample.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.skbroadband.doms.sample.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import static com.skbroadband.doms.sample.entity.QBook.book;
import static org.springframework.util.StringUtils.hasText;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.example.repository
 * @File : ExampleCustomRepositoryImpl
 * @Program :
 * @Date : 2022-11-18
 * @Comment :
 */
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookSupportRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Book> findAll(String title, String firstName, String lastName, Pageable pageable) {
        JPAQuery<Book> content = jpaQueryFactory
                .selectFrom(book)
                .leftJoin(book.author)
                .where(
                        startWith(title, firstName, lastName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            if("title".equals(order.getProperty())) {
                PathBuilder<Book> pathBuilder = new PathBuilder<Book>(Book.class, "book");
                content.orderBy(new OrderSpecifier(
                        order.isAscending() ? Order.ASC : Order.DESC,
                        pathBuilder.get(order.getProperty())));
            }else if("firstName".equals(order.getProperty())) {
                PathBuilder<Book> pathBuilder = new PathBuilder<Book>(Book.class, "book.author");
                content.orderBy(new OrderSpecifier(
                        order.isAscending() ? Order.ASC : Order.DESC,
                        pathBuilder.get(order.getProperty())));
            }
        }

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(book.count())
                .from(book)
                .leftJoin(book.author)
                .where(
                        startWith(title, firstName, lastName)
                );

        return PageableExecutionUtils.getPage(content.fetchJoin().fetch(),
                pageable,
                countQuery::fetchOne);
    }

    private BooleanExpression[] startWith(String title, String firstName, String lastName) {
        return new BooleanExpression[] {
                hasText(title)?book.title.like("%"+title+"%"):null,
                hasText(firstName)?book.author.firstName.like("%"+firstName+"%"):null,
                hasText(lastName)?book.author.lastName.like("%"+lastName+"%"):null
        };
    }

    private OrderSpecifier<?>[] getSortedColumn(Sort sorts){
        return sorts.toList().stream().map(x ->{
            Order order = x.getDirection().name().equals("ASC") ? Order.ASC : Order.DESC;
            SimplePath<Object> filedPath = Expressions.path(Object.class, book, x.getProperty());
            return new OrderSpecifier(order, filedPath);
        }).toArray(OrderSpecifier[]::new);
    }
}
