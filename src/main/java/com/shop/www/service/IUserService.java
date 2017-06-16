package com.shop.www.service;


import java.util.List;

import com.shop.www.model.User;

public interface IUserService {
	 public User getUserById(Integer id);  
	 public void insertUser(List<User> user);
}
