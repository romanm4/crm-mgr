package com.crm.mgr.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class TaskDto implements Serializable {
    private UUID id;
    private int isNewTodo;
    private Date dateOfInitialTask;
    private Date todoDueDate;
    private UUID leadId;
    private UUID todoTypeId;
    private UUID todoDescId;
    private UUID userId;
    private UUID statusId;

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

    public UUID getLeadId() {
        return leadId;
    }

    public void setLeadId(UUID leadId) {
        this.leadId = leadId;
    }

    public UUID getTodoTypeId() {
        return todoTypeId;
    }

    public void setTodoTypeId(UUID todoTypeId) {
        this.todoTypeId = todoTypeId;
    }

    public UUID getTodoDescId() {
        return todoDescId;
    }

    public void setTodoDescId(UUID todoDescId) {
        this.todoDescId = todoDescId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", isNewTodo=" + isNewTodo +
                ", dateOfInitialTask=" + dateOfInitialTask +
                ", todoDueDate=" + todoDueDate +
                ", leadId=" + leadId +
                ", todoTypeId=" + todoTypeId +
                ", todoDescId=" + todoDescId +
                ", userId=" + userId +
                ", statusId=" + statusId +
                '}';
    }
}
