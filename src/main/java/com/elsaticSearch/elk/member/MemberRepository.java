package com.elsaticSearch.elk.member;

import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface MemberRepository extends ElasticsearchRepository<Member, String> {
}
