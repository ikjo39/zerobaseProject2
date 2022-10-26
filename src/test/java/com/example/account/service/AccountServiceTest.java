package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//  SpringBootContextLoader를 실제 application처럼 test용으로 만들어
//  Context를 실제와 동일하게 모든 @Bean들을 생성해서 원하는 test 할 수 있게 해줌

@SpringBootTest
class AccountServiceTest {
    // 계속 이런식으로 Parameter 추가 될수도 있음 -> @Spring-Boot-Test
//    private AccountService accountService = new AccountService(new xxx(...));

    // Bean들이 모두 생성 되어있으면 주입이 가능함 -> 사용할 수 있음
    @Autowired
    private AccountService accountService;

    @BeforeEach // 매 TEST 마다 무조건 수행함, 이외 다른 기능 많ㅇ므
    void init() {
        accountService.createAccount();

    }

    @Test
    void testGetAccount() {
        // given
        // when
        Account account = accountService.getAccount(1L);
        // then
        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE, account.getAccountStatus());
    }

    @DisplayName("TEST 이름 변경")
    @Test
    void testGetAccount2() {
        // given
        // when
        Account account = accountService.getAccount(2L);
        // then
        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE, account.getAccountStatus());
    }

}