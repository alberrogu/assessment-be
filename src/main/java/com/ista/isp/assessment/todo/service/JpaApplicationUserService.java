package com.ista.isp.assessment.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.isp.assessment.todo.exception.ValidationException;
import com.ista.isp.assessment.todo.model.ApplicationUser;
import com.ista.isp.assessment.todo.repository.ApplicationUserRepository;

@Service
public class JpaApplicationUserService implements ApplicationUserService {

	@Autowired
	private ApplicationUserRepository userRepository;

	@Override
	public ApplicationUser signUp(ApplicationUser user) {
		if (user.getUsername() == null || user.getUsername().isBlank()
				|| userRepository.findUserByUsername(user.getUsername()) != null) {
			throw new ValidationException("Username not valid");
		}
		if (user.getUsername() == null || user.getUsername().isBlank()) {
			throw new ValidationException("Password not be null");
		}
		return userRepository.save(user);
	}

	@Override
	public ApplicationUser login(ApplicationUser user) {
		ApplicationUser userSearched = userRepository.findUserByUsername(user.getUsername());
		if (userSearched == null) {
			throw new ValidationException("Username not valid");
		}
		if (!userSearched.getPassword().equals(user.getPassword())) {

			throw new ValidationException("Password not valid");
		}
		return userSearched;
	}

	@Override
	public void deleteUser(Long userId) {

		if (!userRepository.existsById(userId)) {
			throw new ValidationException("Task not exist");
		}
		userRepository.deleteById(userId);

	}

	@Override
	public ApplicationUser getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public boolean existUser(Long userId) {
		return userRepository.existsById(userId);
	}

}
