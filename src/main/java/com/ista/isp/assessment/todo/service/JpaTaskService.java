package com.ista.isp.assessment.todo.service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.isp.assessment.todo.exception.ValidationException;
import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.repository.TaskRepository;

@Service
public class JpaTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private JpaApplicationUserService userService;

	@Override
	public Task createTask(Task task) {
		if (task.getDescription() == null || task.getDescription().isBlank()) {
			throw new ValidationException("Description can not be empty");
		}
		Task savedTask = taskRepository.save(task);
		return savedTask;
	}

	@Override
	public boolean exist(Long taskId) {

		return taskRepository.existsById(taskId);
	}

	@Override
	public boolean exist(Task task) {
		return exist(task.getId());
	}

	@Override
	public Set<Task> getTasks() {
		return taskRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Set<Task> getUserTasks(Long userId) {
		if (userId == null) {
			throw new ValidationException("User can not be null");
		}
		return getTasks().stream().filter(task -> task.getApplicationUser().getId().equals(userId))
				.collect(Collectors.toSet());
	}

	@Override
	public Task updateTask(Task task) {
		if (task.getDescription() == null || task.getDescription().isBlank()) {
			throw new ValidationException("Description can not be empty");
		}
		if (!exist(task)) {
			throw new ValidationException("Task not exist");
		}
		if (!userService.existUser(task.getApplicationUser().getId())) {
			throw new ValidationException("User not found");
		}
		if (task.getIsCompleted() && task.getCompletedDate() == null) {
			task.setCompletedDate(new Date());
		}
		if (!task.getIsCompleted() && task.getCompletedDate() != null) {
			task.setCompletedDate(null);
		}
		return taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long taskId) {
		if (!exist(taskId)) {
			throw new ValidationException("Task not exist");
		}
		taskRepository.deleteById(taskId);

	}

}
