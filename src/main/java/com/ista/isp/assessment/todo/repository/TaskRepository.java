package com.ista.isp.assessment.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ista.isp.assessment.todo.model.Task;

/**
 * @author alberrogu
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
