package com.application.psm.model;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name = "id", nullable=false)
	private Long id;
	@Column(name = "description", nullable=false)
	private String description;
	
	public Role() {
		super();
	}
	public Role(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
