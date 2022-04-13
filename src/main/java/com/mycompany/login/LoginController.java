package com.mycompany.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // handler
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	// @ResponseBody // return "something" to be printed on the screen
	public String showLoginPage() {
		return "login"; // dipatcher serlvet return the login jsp // View Resolver
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name, @RequestParam String password, ModelMap model) {

		if (!loginService.isUserValid(name, password)) {
			model.put("errorMessage", "Invalid Credentials"); // will be directed available in the view
			return "login";
		}

		model.put("name", name); // will be directed available in the view
		return "welcome";
	}
}
