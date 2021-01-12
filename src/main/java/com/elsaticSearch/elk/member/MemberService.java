package com.elsaticSearch.elk.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;

@Log
@Service
@Transactional
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ElasticsearchRestTemplate elasticsearchRestTemplate;

	public void createIndex() {
		elasticsearchRestTemplate.indexOps(Member.class).create();
	}

	public void create(Member member) {
		log.info("MemberService - create()");
		memberRepository.save(member);
	}

	public Iterable<Member> listMember() {
		log.info("MemberService - listMember()");

		return memberRepository.findAll();
	}

	public Page<Member> searchMember(Member member, @Nullable String[] fields, Pageable pageable) {
		log.info("MemberService - searchMember()");

		return memberRepository.searchSimilar(member, fields, pageable);
	}
}
