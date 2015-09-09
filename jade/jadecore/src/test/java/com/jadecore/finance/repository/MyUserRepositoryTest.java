package com.jadecore.finance.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jadecore.finance.abstraction.model.MyUser;
import com.jadecore.finance.abstraction.repository.MyUserRepository;
import com.jadecore.finance.di.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class MyUserRepositoryTest {

	@Autowired
	private MyUserRepository myUserRepository;

	@Test
	public void postUserTest() {
		MyUser myUser = getTestUser();
		myUser = myUserRepository.save(myUser);
		Assert.assertNotNull(myUser);
		Assert.assertTrue(myUser.getId() != null);
		myUserRepository.deleteMyUser(myUser.getId());
	}

	@Test
	public void getUserTest() {
		MyUser myUser = getTestUser();
		myUser = myUserRepository.save(myUser);
		myUser = myUserRepository.get(myUser.getId());
		Assert.assertNotNull(myUser);
		myUserRepository.deleteMyUser(myUser.getId());
	}

	@Test
	public void getUsersTest() {
		MyUser myUser = getTestUser();
		myUser = myUserRepository.save(myUser);
		List<MyUser> users = myUserRepository.list();
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size() > 0);
		for (MyUser user : users) {
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getName());
		}
		myUserRepository.deleteMyUser(myUser.getId());
	}

	@Test
	public void deleteUserTest() {
		MyUser myUser = getTestUser();
		myUser = myUserRepository.save(myUser);
		Boolean result = myUserRepository.deleteMyUser(myUser.getId());
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
