package com.ista.isp.assessment.todo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author alberrogu
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class ApplicationUser extends BaseEntity {

	/** Default serial version uid */
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

}
