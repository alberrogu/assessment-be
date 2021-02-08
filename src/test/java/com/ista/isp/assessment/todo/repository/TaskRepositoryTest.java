package com.ista.isp.assessment.todo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ista.isp.assessment.todo.model.ApplicationUser;
import com.ista.isp.assessment.todo.model.Task;

@DataJpaTest
public class TaskRepositoryTest {

	final String USER_NAME = "alberrogu";

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ApplicationUserRepository userRepository;

	private ApplicationUser applicationUser;

	@BeforeEach
	void setUp() {
		applicationUser = new ApplicationUser();
		applicationUser.setUsername(USER_NAME);
		applicationUser.setPassword("password");
		applicationUser = userRepository.save(applicationUser);
	}

	@AfterEach
	public void cleanUp() {
		taskRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void testNewTask() {
		Task task = TaskTest.createTestTask();
		task.setId(null);
		task.setCreatedDate(null);
		task.setModifiedDate(null);
		task.setUserId(applicationUser.getId());
		task = taskRepository.save(task);
		assertNotNull(task.getId());
		assertNotNull(task.getCreatedDate());
	}

	@Test
	public void testUpdateTask() {
		Task task = TaskTest.createTestTask();
		task.setId(null);
		task.setCreatedDate(null);
		task.setModifiedDate(null);
		task.setUserId(applicationUser.getId());
		;
		task = taskRepository.save(task);
		task.setDescription("new description");
		task.setCompletedDate(new Date());
		task.setIsCompleted(true);
		Task taskSaved = taskRepository.save(task);
		assertEquals(task.getId(), taskSaved.getId());
		assertEquals(task.getCreatedDate(), taskSaved.getCreatedDate());
	}

	@Test
	public void testDeleteTask() {
		Task task = TaskTest.createTestTask();
		task.setId(null);
		task.setCreatedDate(null);
		task.setModifiedDate(null);
		task.setUserId(applicationUser.getId());
		;
		task = taskRepository.save(task);
		Long taskId = task.getId();
		taskRepository.delete(task);
		Optional<Task> taskDeleted = taskRepository.findById(taskId);
		assertThat(taskDeleted);

	}
}
