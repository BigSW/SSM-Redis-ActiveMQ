package com.shop.www.dao;

import com.shop.www.model.User;

public interface IUserDao {

	public User selectByPrimaryKey(Integer id);
	public User selectByName(String name);
	
	public void insertUser(User user);
}
