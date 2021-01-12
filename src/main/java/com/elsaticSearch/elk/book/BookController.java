package com.elsaticSearch.elk.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("search")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("create")
	public ResponseEntity<String> testCreate() {
		log.info("BookController - testCreate()");
		bookService.create();

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<List<Book>> testList() {
		log.info("BookController - testList()");

		Iterable<Book> iter = bookService.findAll();
		Iterator<Book> iterator = iter.iterator();
		List<Book> entityList = new ArrayList<Book>();

		while(iterator.hasNext())
			entityList.add(iterator.next());

		return new ResponseEntity<List<Book>>(entityList, HttpStatus.OK);
	}

	@GetMapping("keyword")
	public ResponseEntity<List<Book>> testSearch(@RequestParam("name")String name, Pageable pageable) {
		log.info("BookController - testSearch() name: " + name);

		Iterable<Book> iter = bookService.findByName(name, pageable);
		Iterator<Book> iterator = iter.iterator();
		List<Book> entityList = new ArrayList<Book>();

		while(iterator.hasNext())
			entityList.add(iterator.next());

		return new ResponseEntity<List<Book>>(entityList, HttpStatus.OK);
	}

	@GetMapping("text")
	public ResponseEntity<List<Book>> fullTextSearch(@RequestParam("param")String param, Pageable pageable) {
		log.info("BookController - fullTextSearch() param: " + param);

		Iterable<Book> iter = bookService.fullTextSearch(param, pageable);
		Iterator<Book> iterator = iter.iterator();
		List<Book> entityList = new ArrayList<Book>();

		while(iterator.hasNext())
			entityList.add(iterator.next());

		return new ResponseEntity<List<Book>>(entityList, HttpStatus.OK);
	}

	@GetMapping("match/all")
	public ResponseEntity<List<Book>> matchAll(Pageable pageable) {
		log.info("BookController - matchAll()");

		Iterable<Book> iter = bookService.matchAll(pageable);
		Iterator<Book> iterator = iter.iterator();
		List<Book> entityList = new ArrayList<Book>();

		while(iterator.hasNext())
			entityList.add(iterator.next());

		return new ResponseEntity<List<Book>>(entityList, HttpStatus.OK);
	}
}
