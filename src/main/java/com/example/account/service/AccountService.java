package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import com.example.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // Service type Bean으로 자동으로 등록
@RequiredArgsConstructor
public class AccountService {
    // @Autowired // 옛날에는 이거나 @Injected 를 씀, 이렇게 해도 동작은 함
    // AccountService를 Test 할때 accountRepository 의존성을 담아내기가 까다로워짐
    // 그래서 필드 삽입이 아니라 생성자 삽입을 하라고 함 -> 매번 하기엔 번거로움
    // -> lombok의 @RequiredArgsConstructor 대체


//    private AccountRepository accountRepository;
// 변수 type을 final로 하면 생성자 만이 변수에 값을 담아 낼 수 있음 -> 변경 X -> 무조건 생성자에 포함되야함
    private final AccountRepository accountRepository;

    // 호출도 필요함
    @Transactional
    public void createAccount() {
        // table에 데이터를 저장
        // Account Entity를 하나 생성 해줘야함 -> @builder 덕에 사용 가능, id는 자동 생성됨
        // 이렇게 저장하는 Logic이 만들어짐
        Account account = Account.builder()
                .accountNumber("40000")
                .accountStatus(AccountStatus.IN_USE)
                .build();
        accountRepository.save(account);
    }


    // optional에서 값을 빼오기 때문에 경고는 뜸 -> 별로 추천 안함
    // 만든걸 DB에서 가져오는 것
    @Transactional
    public Account getAccount(Long id) {

        if (id < 0) {
            throw new RuntimeException("Minus");
        }

        return accountRepository.findById(id).get();
    }

}
