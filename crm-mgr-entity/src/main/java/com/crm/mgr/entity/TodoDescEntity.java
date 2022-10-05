package com.crm.mgr.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="todo_desc")
public class TodoDescEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	private String description;

	@OneToMany(mappedBy = "todoDesc")
	private List<TaskEntity> tasks;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "TodoDescEntity{" +
				"id=" + id +
				", description='" + description + '\'' +
				'}';
	}
}