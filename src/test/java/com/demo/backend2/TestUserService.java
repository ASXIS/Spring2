package com.demo.backend2;

import java.util.Optional;

import com.demo.backend2.entity.User;
import com.demo.backend2.exception.BaseException;
import com.demo.backend2.exception.UserException;
import com.demo.backend2.service.UserSevice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserSevice userSevice;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userSevice.create(
			TestCreateData.email, 
			TestCreateData.password, 
			TestCreateData.name
		);

		// check not null
		
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());
	}

	@Order(2)
	@Test
	void testUpdate() throws UserException {
		Optional<User> opt = userSevice.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		
		User updatedUser = userSevice.updateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userSevice.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userSevice.deleteById(user.getId());

		Optional<User> optDelete = userSevice.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	interface TestCreateData {
		String email = "saw@test";

		String password = "omg";

		String name = "saw";
	}

	interface TestUpdateData {
		String name = "sawsaw";
	}

}
