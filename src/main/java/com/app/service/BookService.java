package com.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Book;
import com.app.repository.BookRepository;

@Service
public class BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	BookRepository br;
	
	public void insertBook(Book b) {
		br.save(b);
		
	}
	
	public List<Book> selectAll() {
		logger.debug("inside selectAll() method");
		List<Book> li = br.findAll();
//		Iterator i = li.iterator();
//		while(i.hasNext()) {
//			Book b = (Book) i.next();
//			System.out.println(u.getId() + "  " + u.getName() + "  " + u.getEmail());
//		}
		return li;
	}
	
	public Book selectOne(int id) {
		Optional<Book> uOpt = br.findById(id);
		return uOpt.get();
	}
	
	public Book update(Book b) {
		br.updateBook(b.getBook_name(), b.getBook_price(), b.getBook_author(), b.getBook_publisher(), b.getBid());
		return b;
	}
	
	public void delete(int id) {
		br.deleteById(id);
	}

}
