package net.slipp.dao.users;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.slipp.domain.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	
	@Test
	public void findById() {
		User user = userDao.findById("javajigi");
		
		System.out.println("User : "+user.getUserId());
	}
	
	@Test
	public void create() throws Exception {
		User user = new User("san","password","san@gamil.com","산");
		userDao.create(user);
		User actual = userDao.findById(user.getUserId());
		
		assertThat(actual, is(user));
				
	}
}
