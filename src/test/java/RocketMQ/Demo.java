package RocketMQ;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import java.util.concurrent.TimeUnit;

public class Demo {
	public static void main(String[] args)throws MQClientException, InterruptedException, RemotingException, MQBrokerException {
		final DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName"); // 设置namesrv地址
		producer.setNamesrvAddr("192.168.86.128:9876;192.168.86.130:9876");
		producer.setInstanceName("Producer");
		producer.start();
		for (int i = 0; i < 10; i++) {
			{
				Message msg = new Message("SELF_TEST_TOPIC", "Tag", "OrderId",
						("你好，TopicTest,my name is xxd_" + i).getBytes());
				System.out.println("message:" + (new String(msg.getBody())));
				System.out.println(msg);
				SendResult sendResult = producer.send(msg);
				System.out.println("Producer.main:sendResult:" + sendResult);
			}
			{
				Message msg = new Message("SELF_TEST_TOPIC", "Tag1", "OrderId1",
						("你好，TopicTest1,my name is xxd_" + i).getBytes());
				System.out.println("message:" + (new String(msg.getBody())));
				SendResult sendResult = producer.send(msg);
				System.out.println("Producer.main:sendResult:" + sendResult);
			}
			TimeUnit.MILLISECONDS.sleep(1000);
		}
		System.out.println("group name:" + producer.getProducerGroup());
	}
}
