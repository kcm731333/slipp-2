package net.slipp.domain.users;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

	private static Validator validator;
	
	@BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }
	
	@Test
	public void userIdWhenIsEmpty() {
		User user = new User("","password","a@a.com","aaaa");
		
		Set<ConstraintViolation<User>> constraintViolations =  validator.validate( user );
		
		assertThat(constraintViolations.size(), is(2));
		for(ConstraintViolation<User> constraintViolation : constraintViolations){
			System.out.println(constraintViolations.iterator().next().getMessage());
		}
		/*
		assertEquals( 1, constraintViolations.size() );
		assertEquals(
		         "may not be null",
		         constraintViolations.iterator().next().getMessage()
		      );
		      */
	}
	
	@Test
	public void matchPassword() throws Exception{
		String password = "password";
		Authenticate authenticate = new Authenticate("userId",password);
		User user = new User("userId",password,"a@a.com","111");
		
		boolean actual = user.matchPassword(authenticate);
		assertTrue(actual);
		
		authenticate = new Authenticate("userId","pass2");
		assertFalse(user.matchPassword(authenticate));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateWhenMisMatchUserId() throws Exception{
		User user = new User("userId","passrowd","name","blue@tigrison.com");
		User updateUser = new User("userI","passrowd","name","san@tigrison.com");
		
		User updatedUser = user.update(updateUser);
	}

}
