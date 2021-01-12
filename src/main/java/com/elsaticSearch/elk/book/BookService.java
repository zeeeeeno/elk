package com.elsaticSearch.elk.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;

@Log
@Service
@Transactional
class BookService {

    @Autowired
    BookRepository bookRepository;

    public void create() {
    	log.info("BookService - create()");

        Book book = new Book();
        book.setId("test");
        book.setName("name");
        book.setPrice(1000);
        book.setSummary("summary");

        bookRepository.save(book);
    }

    public Iterable<Book> findByName(String name, Pageable pageable) {
    	log.info("BookService - findByName() name: " + name);

    	return bookRepository.findByName(name, pageable);
    }

    public Iterable<Book> findAll() {
    	log.info("BookService - findAll()");

    	return bookRepository.findAll();
    }

    public Iterable<Book> fullTextSearch(String param, Pageable pageable) {
    	log.info("BookService - fullTextSearch() param: " + param);
    	return bookRepository.fullTextSearch(param, pageable);
    }

    public Iterable<Book> matchAll(Pageable pageable) {
    	log.info("BookService - matchAll()");
    	return bookRepository.matchAll(pageable);
    }
}