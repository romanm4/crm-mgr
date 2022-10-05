package com.crm.mgr.dto;

import java.io.Serializable;
import java.util.UUID;

public class TodoTypeDto implements Serializable {
    private UUID id;
    private String type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TodoTypeDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
