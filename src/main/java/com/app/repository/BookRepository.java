package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	@Modifying
	@Transactional
	@Query("update Book b set b.book_name=?1, b.book_price=?2, b.book_author=?3, b.book_publisher=?4  where b.bid =?5")
	int updateBook(String book_name, int book_price, String book_author, String book_publisher, int id);	
}
