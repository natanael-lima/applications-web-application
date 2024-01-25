package com.application.psm.model;


import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	private String roleName;
	
	@OneToOne(mappedBy = "role",fetch = FetchType.LAZY)
	private User user;
	
	public Role() {
	}
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
