package com.demo.backend2;

import java.util.List;
import java.util.Optional;

import com.demo.backend2.entity.Address;
import com.demo.backend2.entity.Social;
import com.demo.backend2.entity.User;
import com.demo.backend2.exception.BaseException;
import com.demo.backend2.exception.UserException;
import com.demo.backend2.service.AddressSevice;
import com.demo.backend2.service.SocialSevice;
import com.demo.backend2.service.UserSevice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserSevice userSevice;

	@Autowired
	private SocialSevice socialSevice;

	@Autowired
	private AddressSevice addressSevice;

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
	void testCreateSocial() throws UserException {
		Optional<User> opt = userSevice.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		
		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialSevice.create(
			user, 
			SocialTestCreateData.facebook, 
			SocialTestCreateData.line, 
			SocialTestCreateData.instagram, 
			SocialTestCreateData.tiktok
		);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

	}

	@Order(4)
	@Test
	void testCreateAddress() throws UserException {
		Optional<User> opt = userSevice.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());
		
		User user = opt.get();
		List<Address> addresses = user.getAddresses();
		Assertions.assertTrue(addresses.isEmpty());
		Address address = addressSevice.create(
			user,
			AddressTestCreateData.line1, 
			AddressTestCreateData.line2, 
			AddressTestCreateData.zipcode
		);

		Assertions.assertNotNull(address);
		Assertions.assertEquals(AddressTestCreateData.line1, address.getLine1());
		Assertions.assertEquals(AddressTestCreateData.line2, address.getLine2());
		Assertions.assertEquals(AddressTestCreateData.zipcode, address.getZipcode());

	}

	@Order(9)
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

	interface SocialTestCreateData {

		String facebook = "asxis";

		String line = "asxiss";

		String instagram = "azxis";

		String tiktok = "none";

	}

	interface TestUpdateData {

		String name = "sawsaw";
	}

	interface AddressTestCreateData{
		String line1 = "assa";
		
		String line2 = "3343";

		String zipcode = "54543";

	}

}
