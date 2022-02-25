package com.java.master.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.master.entity.UserModel;
import com.java.master.error.UserException;

@RestController
@RequestMapping("/user")
public interface UserResource {
	
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) throws UserException;

}
