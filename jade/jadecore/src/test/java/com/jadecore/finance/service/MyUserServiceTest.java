package com.jadecore.finance.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jadecore.finance.abstraction.model.MyUser;
import com.jadecore.finance.abstraction.service.MyUserService;
import com.jadecore.finance.di.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class MyUserServiceTest {

	@Autowired
	private MyUserService myUserService;

	@Test
	public void postUserTest() {
		MyUser myUser = getTestUser();
		myUser = myUserService.save(myUser);
		Assert.assertNotNull(myUser);
		Assert.assertTrue(myUser.getId() != null);
		myUserService.deleteMyUser(myUser.getId());
	}

	@Test
	public void getUserTest() {
		MyUser myUser = getTestUser();
		myUser = myUserService.save(myUser);
		myUser = myUserService.get(myUser.getId());
		Assert.assertNotNull(myUser);
		myUserService.deleteMyUser(myUser.getId());
	}

	@Test
	public void getUsersTest() {
		MyUser myUser = getTestUser();
		myUser = myUserService.save(myUser);
		List<MyUser> users = myUserService.list();
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size() > 0);
		for (MyUser user : users) {
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getName());
		}
		myUserService.deleteMyUser(myUser.getId());
	}

	@Test
	public void deleteUserTest() {
		MyUser myUser = getTestUser();
		myUser = myUserService.save(myUser);
		Boolean result = myUserService.deleteMyUser(myUser.getId());
		Assert.assertNotNull(result);
		Assert.assertTrue(result);
	}

	private MyUser getTestUser() {
		MyUser myUser = new MyUser();
		myUser.setName("Test User");
		myUser.setContactNo("+91 9999999999");
		myUser.setEmail("test.email@dummy.com");
		return myUser;
	}
}
