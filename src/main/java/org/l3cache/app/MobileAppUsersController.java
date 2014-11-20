package org.l3cache.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/users")
public class MobileAppUsersController {
	private static final Logger log = LoggerFactory
			.getLogger(MobileAppUsersController.class);
	
	
	@RequestMapping(value="/new", method = {RequestMethod.POST, RequestMethod.GET})
	public void newUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, Model model) {
		
		
		model.addAttribute("status", "10");
	}
	
	@RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
	public void loginUser(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, Model model) {
		
		
		model.addAttribute("status", "10");
	}
	
	@RequestMapping(value="/{uid}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public void loginUser(@PathVariable("uid") long uid,
			@RequestParam(value = "password") String password, Model model) {
		
		
		model.addAttribute("status", "10");
	}

}
