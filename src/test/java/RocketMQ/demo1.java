package RocketMQ;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import java.util.List;

/** * Created by dong on 2016/12/26. */
public class demo1 {
	public static void main(String[] args) throws MQClientException {
		final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumberGroupName");
		consumer.setNamesrvAddr("192.168.86.128:9876;192.168.86.130:9876");
		consumer.setInstanceName("Consumer");
		consumer.subscribe("SELF_TEST_TOPIC", "Tag");
		consumer.subscribe("SELF_TEST_TOPIC", "*");
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
				for (Message msg :msgs) {
					System.out.println("Consumer.consumeMessage:" + (new String(msg.getBody())));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		System.out.println("clientIP:" + consumer.getClientIP());
		consumer.start();
		System.out.println("Consumer Started.");
	}
}