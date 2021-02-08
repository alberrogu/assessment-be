package com.ista.isp.assessment.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ista.isp.assessment.todo.model.ApplicationUser;

/**
 * @author alberrogu
 *
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

	ApplicationUser findUserByUsername(String username);

}
