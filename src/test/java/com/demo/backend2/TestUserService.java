package com.demo.backend2;

import com.demo.backend2.entity.User;
import com.demo.backend2.exception.BaseException;
import com.demo.backend2.service.UserSevice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class TestUserService {

	@Autowired
	private UserSevice userSevice;

	@Test
	void testCreate() throws BaseException {
		User user = userSevice.create(
			TestData.email, 
			TestData.password, 
			TestData.name
		);

		// check not null
		
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());
	}

	@Test
	void testUpdate() {
	}

	@Test
	void testDelete() {
	}

	interface TestData {
		String email = "saw@test";

		String password = "omg";

		String name = "saw";
	}

}
