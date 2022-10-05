package com.crm.mgr.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="role")
public class RoleEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(columnDefinition = "uuid", name = "id", updatable = false, nullable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;

	private String role;

	@OneToMany(mappedBy="role")
	private List<UserEntity> users;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "RoleEntity{" +
				"id=" + id +
				", role='" + role + '\'' +
				'}';
	}
}