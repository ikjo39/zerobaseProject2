package com.example.account.repository;

import com.example.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Spring에서 JPA를 훨씬 더 쓰기 쉽게 만들어주는 툴임
// <Entity, PK의 type>
@Repository // Bean 등록
public interface AccountRepository extends JpaRepository<Account, Long> {

}
