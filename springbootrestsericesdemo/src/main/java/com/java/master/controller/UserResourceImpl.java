package com.java.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.master.entity.User;
import com.java.master.entity.UserModel;
import com.java.master.error.UserException;
import com.java.master.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResourceImpl implements UserResource {

	@Autowired
	UserService userService;
	
	public ResponseEntity<String> registerUser(UserModel userModel) throws UserException {
		if(userModel.getPassword().equals(userModel.getMatchingPassword())) {
		User user = new User(userModel.getFirstName(), userModel.getLastName(), userModel.getEmail(), userModel.getPassword(),"USER",true,userModel.getMobile());
		Boolean flag = userService.registeruser(user);
		if(flag == true) {
			return new ResponseEntity<String>("User Registered successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("User Registered successfully", HttpStatus.OK);
		}
	}else {
		return new ResponseEntity<String>("Password and Confirm Password does not match", HttpStatus.BAD_REQUEST);
	}

}
}
