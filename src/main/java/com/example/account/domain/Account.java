package com.example.account.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder    // builder로 객체 생성하기 위함 -> NoArgsConstructor, AllArgsConstructor 필요
@Entity // 일종의 설정 Class, Java 객체처럼 보이지만 사실은 설정임
public class Account {
    @Id // PK 지정
    @GeneratedValue
    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)    // DB에 저장시 enum은 순서가 저장되는데 헷갈림 방지를 위해 문자열 그대로 저장함
    private AccountStatus accountStatus;

}
