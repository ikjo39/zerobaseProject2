package com.example.account.dto;

import lombok.*;

import java.time.LocalDateTime;

public class CreateAccount {
    @Getter
    @Setter
    public static class Request {
        // InnerClass 만들어 Naming하면 명시적으로 알 수 있음
        private Long userId;
        private Long userInitialBalance;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        // InnerClass 만들어 Naming하면 명시적으로 알 수 있음
        private Long userId;
        private String accountNumber;
        private LocalDateTime registeredAt;
        

    }
}
