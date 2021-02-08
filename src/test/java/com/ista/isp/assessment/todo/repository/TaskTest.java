package com.ista.isp.assessment.todo.repository;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ista.isp.assessment.todo.model.Task;

/**
 * @author alberrogu
 *
 */
public class TaskTest {

	public static Task createTestTask() {
		Task task = new Task();
		task.setCreatedDate(new Date());
		task.setDescription("Test task");
		task.setId((long) Math.random());
		return task;
	}

	@Test
	public void testCreate() {
		Task task = createTestTask();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(task);
			System.out.println(result);
			task = mapper.readValue(result, Task.class);
			System.out.println(task);

		} catch (IOException e) {
		}
	}

	@Test
	public void testUpdate() {
		Task task = createTestTask();
		task.setModifiedDate(new Date());
		task.setCompletedDate(new Date());
		task.setIsCompleted(true);
		try {
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(task);
			System.out.println(result);
			task = mapper.readValue(result, Task.class);
			System.out.println(task);

		} catch (IOException e) {
		}
	}

}
