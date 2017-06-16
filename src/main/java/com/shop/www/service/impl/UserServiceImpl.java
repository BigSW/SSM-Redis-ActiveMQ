package com.shop.www.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.www.dao.IUserDao;
import com.shop.www.model.User;
import com.shop.www.service.IUserService;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {
	Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private IUserDao userDao;

	public User getUserById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
	
	public User selectByName(String name) {
		return userDao.selectByName(name);
	}

	@Transactional(propagation=Propagation.REQUIRED) 
	public void insertUser(List<User> user){
		for(int i=0;i<user.size();i++){
			try {
				userDao.insertUser(user.get(i));
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
}
