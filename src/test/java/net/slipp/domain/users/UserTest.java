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

}
