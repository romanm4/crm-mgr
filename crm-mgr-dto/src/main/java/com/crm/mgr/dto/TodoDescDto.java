package com.crm.mgr.dto;

import java.io.Serializable;
import java.util.UUID;

public class TodoDescDto implements Serializable {
    private UUID id;
    private String description;

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

    @Override
    public String toString() {
        return "TodoDescDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
