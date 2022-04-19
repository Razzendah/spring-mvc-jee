package com.mycompany.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller // handler
@SessionAttributes("name") // second form
public class TodoController {

	@Autowired
	TodoService todosService;

	private Log logger = LogFactory.getLog(TodoController.class);

	@InitBinder // se ejecuta al inicio para formatear fechas en toda la aplicación
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	// @ResponseBody // return "something" to be printed on the screen
	public String listTodos(/*@RequestParam String user,*/ ModelMap model) throws Exception {

		// model.addAttribute("name", user); // forma 1
		model.addAttribute("todos", todosService.retrieveTodos(getUser()));
		// throw new Exception(); -> test error handling
		return "list-todos"; // dipatcher serlvet return the login jsp // View Resolver
	}

	private String getUser() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {

		model.addAttribute("todo", new Todo(0, getUser(), "", new Date(), false));

		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todosService.addTodo(getUser(), todo.getDesc(), new Date(), false);
		model.clear();

		return "redirect:list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(@QueryParam(value = "id") int id, ModelMap model) {
		Todo todo = todosService.retrieveTodo(id);
		model.addAttribute("todo", todo);

		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodoPost(@Valid Todo todo, ModelMap model, BindingResult result) {

		if (result.hasErrors())
			return "todo";

		todo.setUser(getUser()); // se saca de sesión el nombre, ya que al hacerlo por hidden values form, puede haber ataque de Man in the Middle
		todosService.updateTodo(todo);

		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@QueryParam(value = "id") int id, ModelMap model) {

		todosService.deleteTodo(id);
		model.clear();

		return "redirect:list-todos";
	}

	// use in this, for send a specific message for this controller
	@ExceptionHandler(value = Exception.class)
	public String handleError(HttpServletRequest request, Exception ex) {
		logger.error("Request: " + request.getRequestURL() + " Threw an exception ", ex);
		return "error-specific";
	}
}
