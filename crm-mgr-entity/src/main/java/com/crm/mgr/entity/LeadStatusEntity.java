package com.crm.mgr.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="lead_status")
public class LeadStatusEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	private String status;

	@OneToMany(mappedBy="leadStatus")
	private List<LeadEntity> leads;

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

	public List<LeadEntity> getLeads() {
		return leads;
	}

	public void setLeads(List<LeadEntity> leads) {
		this.leads = leads;
	}

	@Override
	public String toString() {
		return "LeadStatusEntity{" +
				"id=" + id +
				", status='" + status + '\'' +
				'}';
	}
}