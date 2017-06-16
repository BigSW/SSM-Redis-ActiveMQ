package shop;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.www.cache.RedisCacheTransfer;

import redis.clients.jedis.Jedis;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-redis.xml" })
public class redisTest {

	
	@Inject
	private StringRedisTemplate stringRedisTemplate;
	@Test
	public void test() {
		stringRedisTemplate.opsForValue().set("aaa", "hello world");
		System.out.println("success");
		System.out.println(stringRedisTemplate.opsForValue().get("test3"));
	}

	
	/*
	 * ≤‚ ‘ «∑Òƒ‹pingÕ®
	 * 
	 * */
	/*public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.86.128",6379);
		redis.auth("redis");
		String ping = redis.ping();
		redis.set("sw", "1");
		System.out.println(ping);
	}*/
}
