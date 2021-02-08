package com.ista.isp.assessment.todo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.model.ApplicationUser;
import com.ista.isp.assessment.todo.service.ApplicationUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private ApplicationUserService userService;

	@GetMapping(path = "/sign-up/{username}/{password}")
	public ApplicationUser signUp(@PathVariable String username, @PathVariable String password) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setPassword(password);
		applicationUser.setUsername(username);
		return userService.signUp(applicationUser);

	}

	@GetMapping(path = "/login/{username}/{password}")
	public ApplicationUser login(@PathVariable String username, @PathVariable String password) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setPassword(password);
		applicationUser.setUsername(username);
		return userService.login(applicationUser);

	}
}
