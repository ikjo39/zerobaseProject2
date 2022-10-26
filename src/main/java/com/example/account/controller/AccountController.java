package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/*
*       Layered Architecture 는 외부에서는 Controller로만 접속하고
*       Controller는 Service로만 접속하고
*       Service는 Repository로 접속하는 순차적인 계층화된 구조를 만듬
*       -> Controller는 Service만 의존하게 함
* */

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @GetMapping("/get-lock")
    public String getLock(){
        return  redisTestService.getLock();
    }

    @GetMapping("/create-account")
    public String createAccount() {
        accountService.createAccount();
        return "Success!!";
    }

    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id) {
        if (id < 0) {
            throw new RuntimeException("Minus");
        }

        return accountService.getAccount(id);
    }


}