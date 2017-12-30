package redis01;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

	private Logger log = Logger.getGlobal();
	
	@Test
	public void jedisTest() {
		Jedis jedis = new Jedis("192.168.234.131",6379);
		String username = jedis.get("name");
		System.out.println(username);
		jedis.close();
	}
	
	@Test
	public void jedisTest2() {
		Jedis jedis = new Jedis("192.168.234.130",6379);
		Long exist = jedis.exists("username","user","name");
		System.out.println(exist);
		jedis.close();
	}
	
	@Test
	public void jedisTest3() {
		Jedis jedis = new Jedis("192.168.234.130",6379);
		Set<String> keys = jedis.keys("*");
		log.info(keys.toString());
		//设定存活时间倒计时
		jedis.expire("username", 100);
		log.info("ttl(username):"+jedis.ttl("username").toString());
		//取消存活时间倒计时
		jedis.persist("username");
		log.info("ttl(username):"+jedis.ttl("username"));
		
		List<String> users = jedis.lrange("users", 0, -1);
		log.info(users.toString());
		jedis.close();
	}
	
	@Test
	public void jedisPoolTest() {
		//创建配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMaxIdle(30);
		config.setMaxWaitMillis(5000);
		//创建jedis连接池
		JedisPool jedisPool = new JedisPool(config, "192.168.234.130", 6379);
		//从连接池获取jedis对象
		Jedis jedis = jedisPool.getResource();
		log.info(jedis.get("username"));
		jedis.close();
		jedisPool.close();
	}
}

















