package net.slipp.web.users;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	public String form(Model model){
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	
	@RequestMapping(value="" , method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			System.out.println("Binding Result has error");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for(ObjectError error : errors){
				System.out.println("error : "+ error.getCode());
			}
			
			return "users/form";
		}
		
		System.out.println("User : "+user);
		userDao.create(user);
		System.out.println("DataBase + "+userDao.findById(user.getUserId()));
		
		return "redirect:/";
		
	}
}
