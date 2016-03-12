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
import net.slipp.domain.users.Authenticate;
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
	
	@RequestMapping(value="/login/form")
	public String loginForm(Model model){
		model.addAttribute("authenticate", new Authenticate());
		return "users/login";
	}
	
	@RequestMapping(value="/login")
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			return "users/login";
		}
		
		User user = userDao.findById(authenticate.getUserId());
		if(user == null){
			//TODO 에러 처리 - 존재하지 않는 사용자입니다.
		}
		
		if(!user.getPassword().equals(authenticate.getPassword())){
			//TODO 에러처리 - 비번이틀리다.
		}
		
		//TODO 세션에 사용자 정보 저장 
		
		
		return "users/login";
	}
	
	
}
