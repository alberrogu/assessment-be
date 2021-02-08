package com.ista.isp.assessment.todo.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping(path = "/{id}")
	public Set<Task> getByUser(@PathVariable Long id) {
		return taskService.getUserTasks(id);
	}

	@PostMapping(path = "/new")
	public Task newTask(@RequestBody Task taskMessage) {
		return taskService.createTask(taskMessage);

	}

	@PostMapping(path = "/update")
	public Task updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);

	}

	@DeleteMapping(path = "/delete/{id}")
	public void newTask(@PathVariable Long id) {
		taskService.deleteTask(id);

	}

}
