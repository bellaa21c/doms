package com.skbroadband.doms.sample.repository;

import com.skbroadband.doms.sample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, BookSupportRepository {
    /**
     * fetch join
     * @return
     */
    @Override
    @Query("select t from Book t join fetch t.author where t.id=:id")
    Optional<Book> findById(@Param("id") Long id);
}