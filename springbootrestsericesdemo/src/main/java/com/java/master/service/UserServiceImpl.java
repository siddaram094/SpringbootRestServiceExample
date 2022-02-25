package com.java.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.master.entity.User;
import com.java.master.error.UserException;
import com.java.master.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public boolean registeruser(User user) throws UserException {
		// TODO Auto-generated method stub
		User userDb;
		User userFromDb =userRepository.findByMobile(user.getMobile()); 
		System.out.println("user from user service impl"+userFromDb);
		if(userFromDb==null) {
		 userDb = userRepository.save(user);
		}
		else {
			throw new UserException("User already exists");
		}
		if(userDb !=null ) {
		return true;
		}else {
			return false;
		}
	}

}
