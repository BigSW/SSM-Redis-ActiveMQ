package shop;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.www.mq.send.sendMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mq.xml" })
public class MQTest {
	@Inject
	private sendMessageService service;
	
	@Test
	public void send(){
		for(int i=0;i<3;i++){
			service.send("你好:"+i);
		}
	}
}
