package com.ista.isp.assessment.todo.service;

import com.ista.isp.assessment.todo.model.ApplicationUser;

public interface ApplicationUserService {

	ApplicationUser signUp(ApplicationUser user);

	ApplicationUser login(ApplicationUser user);

	void deleteUser(Long userId);

	boolean existUser(Long userId);

	ApplicationUser getUserById(Long userId);

}
