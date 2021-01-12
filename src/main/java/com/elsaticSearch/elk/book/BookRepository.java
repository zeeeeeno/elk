package com.elsaticSearch.elk.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {
    Page<Book> findByName(String name, Pageable pageable);

    @Query("{\"match\": {\"price\": {\"query\": \"?0\"}}}")
    Page<Book> fullTextSearch(String name, Pageable pageable);

    @Query("{\"match\": {\"query\": \"\"}}")
    Page<Book> matchAll(Pageable pageable);
}