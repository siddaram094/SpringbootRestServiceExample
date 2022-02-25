package com.java.master.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastname;
	private String email;
	private String password;
	private String role;
	private Long mobile;
	private boolean enabled = false;
	public User(String firstName, String lastname, String email, String password, String role, boolean enabled,Long mobile) {
		super();
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.mobile = mobile;
	}
	

}
