package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


// Junit을 그대로 쓰면 못써서 확장팩을 testClass에 달아줌
@ExtendWith(MockitoExtension.class)
class AccountServiceTest_Mockito {

    // AccountRepository 가 의존되어 있는데 이거 가짜로 만들어 넣어줘야함

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks // accountRepository를 넣어줌
    private AccountService accountService;

    @DisplayName("계좌 조회 성공")
    @Test
    void testXXX() {
        // given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder() // findById()는 Optional type을 넘겨줌
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("65789")
                        .build()));
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        // when
        Account account = accountService.getAccount(4555L);


        // then - getAccount()시 save()는 실행되면 안됨
        verify(accountRepository, times(1))
                .findById(captor.capture());
        verify(accountRepository, times(0)).save(any());
        assertEquals(4555L, captor.getValue());
        assertNotEquals(452355L, captor.getValue());
        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());

    }

    @DisplayName("계좌 조회 실패 - 음수로 조회")
    @Test
    void testFailedToSearchAccount() {
        // given
        // when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> accountService.getAccount(-10L)
                // 동작을 뜻함
        );


        // then
        assertEquals("Minus", exception.getMessage());

    }


    @DisplayName("TEST 이름 변경")
    @Test
    void testGetAccount() {
        // given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder() // findById()는 Optional type을 넘겨줌
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("65789")
                        .build()));
        // when
        Account account = accountService.getAccount(4555L);


        // then
        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }

    @Test
    void testGetAccount2() {
        // given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder() // findById()는 Optional type을 넘겨줌
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("65789")
                        .build()));
        // when
        Account account = accountService.getAccount(4555L);


        // then
        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }

}