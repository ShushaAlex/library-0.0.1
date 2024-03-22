package com.example.library001.repository;

import com.example.library001.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findByTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book SET isAvailable = :isAvailable WHERE id = :bookID")
    void updateIsAvailable(@Param("bookID") Long bookID, @Param("isAvailable") boolean isAvailable);
}
