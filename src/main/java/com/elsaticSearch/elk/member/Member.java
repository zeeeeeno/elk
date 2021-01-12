package com.elsaticSearch.elk.member;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName="member")
public class Member {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
}
