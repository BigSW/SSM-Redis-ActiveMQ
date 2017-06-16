package com.shop.www.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shop.www.model.User;
import com.shop.www.service.impl.UserServiceImpl;

@Controller
public class UserController {

	@Resource
	private UserServiceImpl impl;

	@RequestMapping("/user/getUsers")
	public void getUsers() {
		Integer id=37;
		User user = impl.getUserById(id);
		System.out.println(user.toString());
	}
	
	@RequestMapping("/user/name")
	public void name() {
		String name ="sss";
		User user = impl.selectByName(name);
		System.out.println(user.toString());
	}

	@RequestMapping("/user/insertUsers")
	public void insertUsers() {
		List<User> list = new ArrayList<User>();
		for(int i=56;i<60;i++){
			User user = new User();
			user.setId(i);
			user.setName("heloo");
			list.add(user);
		}
		
		impl.insertUser(list);
	}
}
