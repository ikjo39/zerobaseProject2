package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class) // test 하려는 class 명시
class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private RedisTestService redisTestService;
    // Injection 은 이미 처음에 명시했기에 하지 않음 - 자동으로 Bean등록됐기 때문

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successGetAccount() throws Exception {
        // given
        given(accountService.getAccount(anyLong()))
                .willReturn(Account.builder()
                        .accountNumber("3456")
                        .accountStatus(AccountStatus.IN_USE)
                        .build());
        // when

        // then
        // 객체의 응답형식이 HTTP 프로토콜로 오기에 일반적으로는 불가능
        mockMvc.perform(get("/account/876"))
                .andDo(print()) // get을 했을 때 응답 값을 화면에 표시
                .andExpect(jsonPath("$.accountNumber").value("3456"))
                .andExpect(jsonPath("$.accountStatus").value("IN_USE"))
                .andExpect(status().isOk());
        /*
        *       좀 친절하진 않음
        *       framework 동작원리와 사용법을 어느 정도 외우고 있어야함
        *       어쩔 수 없음
        * */
    }
}
