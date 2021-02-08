package com.ista.isp.assessment.todo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ista.isp.assessment.todo.exception.ValidationException;
import com.ista.isp.assessment.todo.model.ApplicationUser;
import com.ista.isp.assessment.todo.model.Task;
import com.ista.isp.assessment.todo.repository.TaskRepository;
import com.ista.isp.assessment.todo.repository.TaskTest;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	final String USER_NAME = "alberrogu";

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private JpaApplicationUserService userService;

	@InjectMocks
	private JpaTaskService taskService;

	private ApplicationUser user;

	@BeforeEach
	public void setUp() {
		user = new ApplicationUser();
		user.setUsername(USER_NAME);
		user.setPassword("password");
		user.setId(1L);
	}

	@Test
	public void testCreateTask() {
		final Task task = TaskTest.createTestTask();
		task.setId(null);
		task.setCreatedDate(null);
		task.setModifiedDate(null);
		task.setUserId(user.getId());
		when(taskRepository.save(any())).thenReturn(taskSaved(task));
		// OK
		Task taskSaved = taskService.createTask(task);
		assertNotNull(taskSaved.getId());
		assertNotNull(taskSaved.getCreatedDate());
		// KO
		task.setDescription("");
		assertThrows(ValidationException.class, () -> taskService.createTask(task));
		task.setDescription(null);
		assertThrows(ValidationException.class, () -> taskService.createTask(task));
	}

	@Test
	public void testUpdateTask() {
		final Task task = TaskTest.createTestTask();
		task.setCreatedDate(null);
		task.setModifiedDate(null);
		task.setUserId(user.getId());
		task.setId(1L);
		task.setIsCompleted(true);
		task.setCreatedDate(new Date());
		// KO task not exists
		assertThrows(ValidationException.class, () -> taskService.updateTask(task));
		// KO exist user
		assertThrows(ValidationException.class, () -> taskService.updateTask(task));
		// OK
		when(taskService.exist(1L)).thenReturn(true);
		when(userService.existUser(1L)).thenReturn(true);
		when(taskRepository.save(any())).thenReturn(task);

		assertNotNull(taskService.updateTask(task));
	}

	private Task taskSaved(Task task) {
		Task taskSaved = new Task();
		taskSaved.setId(1L);
		taskSaved.setCreatedDate(new Date());
		taskSaved.setDescription(task.getDescription());
		return taskSaved;
	}
}
