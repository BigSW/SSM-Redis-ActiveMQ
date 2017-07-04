package RocketMQ;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

public class Customer1 {
	/*public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rmq-group");

		consumer.setNamesrvAddr("192.168.86.128:9876");
		consumer.setInstanceName("rmq-instance");
		customer1(consumer);
		customer2(consumer);
	}*/
	public static void main(String[] args) throws MQClientException {

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rmq-group");

		consumer.setNamesrvAddr("192.168.86.128:9876");
		consumer.setInstanceName("rmq-instance");
		consumer.subscribe("Topic-test", "TagA");

		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					System.out.println("Customer-1"+new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.out.println("Consumer Started.");
	}

	private static void customer2(DefaultMQPushConsumer consumer) throws MQClientException {

		consumer.subscribe("Topic-test", "TagB");

		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					System.out.println("Customer2" + new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.out.println("Consumer Started.");
	}

	private static void customer1(DefaultMQPushConsumer consumer) throws MQClientException {
		consumer.subscribe("Topic-test", "TagA");

		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					System.out.println("Customer1:" + new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.out.println("Consumer Started.");
	}
}
