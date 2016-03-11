package net.slipp.web.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.slipp.dao.users.UserDao;
import net.slipp.domain.users.User;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/form")
	public String form(){
		return "users/form";
	}
	
	
	@RequestMapping(value="/create" , method=RequestMethod.POST)
	public String create(User user){
		System.out.println("User : "+user);
		userDao.create(user);
		System.out.println("DataBase + "+userDao.findById(user.getUserId()));
		
		return "users/form";
		
	}
}
