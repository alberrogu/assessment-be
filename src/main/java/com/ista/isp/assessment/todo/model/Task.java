package com.ista.isp.assessment.todo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "tasks")
public class Task extends BaseEntity {

	/** The default serial version UID */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "descritpion")
	private String description;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "completed_date")
	private Date completedDate;

	@Column(name = "is_completed")
	private Boolean isCompleted = false;

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		modifiedDate = new Date();
	}

}
