package com.app.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Book;
import com.app.service.BookService;

@Controller
@RequestMapping("/book/")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private BookService bs;
	
	//when user presses addBook link
	@GetMapping("/addBook")
	//book object is created and sent to the form
	public String bookForm(@ModelAttribute("book") Book book){
		return "addBook";
	}
	
	@PostMapping("add")
	public String addBook(@ModelAttribute("book") Book book, Model m) {
		bs.insertBook(book);
		return "redirect:viewBook";
	}
	
	@GetMapping("/viewBook")
	public String viewBook(Model m) {
		logger.debug("inside BookController.bs.selectAll() method");
		logger.info("this is the end of method selectAll() coming out of methods");
		m.addAttribute("book", bs.selectAll());
		return "viewBook";
	}
	
	@GetMapping("/updateBook")
	public String showUpdateBookForm(@ModelAttribute("book") Book book) {
		//m.addAttribute("updBook", new Book());
		return "updateBook";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public String updateBook(@ModelAttribute("book") Book book) {
		//System.out.println("Book to update ________" + book.getBid() + "  " + book.getBook_name());
		bs.update(book);
		return "redirect:viewBook";
	}
	
	@RequestMapping("/deleteBook/{bid}")
	public String deleteBook(@PathVariable("bid") int id) {
//		//updBook.setBid(id);
		System.out.println("Book to delete ________" + id);
		bs.delete(id);
		return "/home";
	}

}
