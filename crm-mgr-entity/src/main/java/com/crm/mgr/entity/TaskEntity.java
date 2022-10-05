package com.crm.mgr.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="task")
public class TaskEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	@Column(name="is_new_todo")
	private int isNewTodo;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_initial_task")
	private Date dateOfInitialTask;

	@Temporal(TemporalType.DATE)
	@Column(name="todo_due_date")
	private Date todoDueDate;

	@ManyToOne
	@JoinColumn(name="lead_id")
	private LeadEntity lead;

	@ManyToOne
	@JoinColumn(name="todo_type_id")
	private TodoTypeEntity todoType;

	@ManyToOne
	@JoinColumn(name="todo_desc_id")
	private TodoDescEntity todoDesc;

	@ManyToOne
	@JoinColumn(name="sales_rep_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name="status_id")
	private TaskStatusEntity taskStatus;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getIsNewTodo() {
		return isNewTodo;
	}

	public void setIsNewTodo(int isNewTodo) {
		this.isNewTodo = isNewTodo;
	}

	public Date getDateOfInitialTask() {
		return dateOfInitialTask;
	}

	public void setDateOfInitialTask(Date dateOfInitialTask) {
		this.dateOfInitialTask = dateOfInitialTask;
	}

	public Date getTodoDueDate() {
		return todoDueDate;
	}

	public void setTodoDueDate(Date todoDueDate) {
		this.todoDueDate = todoDueDate;
	}

	public LeadEntity getLead() {
		return lead;
	}

	public void setLead(LeadEntity lead) {
		this.lead = lead;
	}

	public TodoTypeEntity getTodoType() {
		return todoType;
	}

	public void setTodoType(TodoTypeEntity todoType) {
		this.todoType = todoType;
	}

	public TodoDescEntity getTodoDesc() {
		return todoDesc;
	}

	public void setTodoDesc(TodoDescEntity todoDesc) {
		this.todoDesc = todoDesc;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TaskStatusEntity getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatusEntity taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "TaskEntity{" +
				"id=" + id +
				", isNewTodo=" + isNewTodo +
				", dateOfInitialTask=" + dateOfInitialTask +
				", todoDueDate=" + todoDueDate +
				'}';
	}
}