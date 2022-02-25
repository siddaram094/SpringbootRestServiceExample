package com.java.master.service;

import org.springframework.stereotype.Service;

import com.java.master.entity.User;
import com.java.master.error.UserException;

@Service
public interface UserService {

	public boolean registeruser(User user) throws UserException;
}
