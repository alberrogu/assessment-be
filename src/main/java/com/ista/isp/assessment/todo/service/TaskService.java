package com.ista.isp.assessment.todo.service;

import java.util.Set;

import com.ista.isp.assessment.todo.model.Task;

/**
 * @author alberrogu
 *
 */
public interface TaskService {

	Task createTask(Task task);

	boolean exist(Long taskId);

	boolean exist(Task task);

	Set<Task> getTasks();

	Set<Task> getUserTasks(Long userId);

	Task updateTask(Task task);

	void deleteTask(Long taskId);
}
