package com.elsaticSearch.elk.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService memberService;

	@PostMapping("create")
	public ResponseEntity createMember(@ModelAttribute("member") Member member) {
		log.info("MemberController - createUser() member: " + member);

		memberService.create(member);

		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity listMember() {
		log.info("MemberController - listMember()");
		Iterable<Member> memberList = memberService.listMember();

		return new ResponseEntity(memberList, HttpStatus.OK);
	}

	@GetMapping("search/{keywords}")
	public ResponseEntity searchMember(Member member, @RequestParam("keywords") String[] keywords, Pageable pageable) {
		log.info("MemberController - listMember()");
		Page<Member> memberPage = memberService.searchMember(member, keywords, pageable);

		return new ResponseEntity(memberPage, HttpStatus.OK);
	}
}
