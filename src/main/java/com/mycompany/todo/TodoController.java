package com.mycompany.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // handler
public class TodoController {

	@Autowired
	TodoService todosService;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	// @ResponseBody // return "something" to be printed on the screen
	public String showLoginPage(ModelMap model) {
		model.addAttribute("todos", todosService.retrieveTodos("in28Minutes"));

		return "list-todos"; // dipatcher serlvet return the login jsp // View Resolver
	}

}
