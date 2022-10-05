package com.crm.mgr.dto;

import java.io.Serializable;
import java.util.UUID;

public class TaskStatusDto implements Serializable {
    private UUID id;
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskStatusDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
