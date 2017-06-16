package shop;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.www.model.User;
import com.shop.www.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })

public class TestMyBatis {

	@Inject
	UserServiceImpl userServiceImpl=null;

	 @Test  
	public void get() {
		User user = userServiceImpl.getUserById(11);
		System.out.println(user.toString());
		//return user;
	}
	 
}
