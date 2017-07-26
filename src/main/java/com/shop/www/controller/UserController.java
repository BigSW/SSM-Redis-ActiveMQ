package com.shop.www.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shop.www.model.User;
import com.shop.www.service.impl.UserServiceImpl;

@Controller
public class UserController {

	@Resource
	private UserServiceImpl impl;

	@RequestMapping("/register")
	public String register() {
		return "reg";
	}
	@RequestMapping("/user/getUsers")
	public void getUsers() {
		Integer id=37;
		User user = impl.getUserById(id);
		System.out.println(user.toString());
	}
	
	
	/**短信验证实现原理(采用互动无线)
	 * 通过获取随机数得到mobile_code，保存在一个map中，然后将mobile_code发送到手机上。
	 * 用户再次输入，将用户输入的验证码与map中的验证码比对，如果相同，说明用户验证成功
	 * 否则失败
	 * @param request
	 * @param phone
	 * @return
	 */
	@RequestMapping("user/checkCode")
	public String checkCode(HttpServletRequest request,String phone){
		String result = null;
		String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

		int mobile_code = (int)((Math.random()*9+1)*100000); //获取随机数

		String account = "C42342163"; //查看用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
		String password = "2e14e015428b9fa8445694b49b1c73c2";  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
		//String mobile = request.getParameter("18854992459");
		String mobile=phone;
		String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		try {

			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);//允许连接提交信息
			connection.setRequestMethod("POST");//网页提交方式“GET”、“POST”
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("account="+account);
			sb.append("&password="+password);
			sb.append("&mobile="+mobile);
			sb.append("&content="+content);
			OutputStream os = connection.getOutputStream();
			os.write(sb.toString().getBytes());
			os.close();

			String line;
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return result;
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
