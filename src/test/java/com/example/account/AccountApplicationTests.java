package com.example.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/* 오류가 나는 이유--
	Redis가 6379 Port 이미 돌아가고 있기 떄문
	AccountApplication 이 뜨면서 Embeded-Redis가 포트를 씀
	Test를 돌면서 AccountApplication 쓰려고하는데 먼저 개입해서 쓰고 있기 때문
*/

@SpringBootTest
class AccountApplicationTests {

	@Test
	void contextLoads() {
	}

}
