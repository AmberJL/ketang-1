package com.example.demo.c;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;



/**
 *
 * redis工具类
 */
public class RedisClient{

	static ShardedJedisPool shardedJedisPool;// 切片连接池

	// 静态初始化块，只在类加载的时候初始化一次，且只能初始化静态成员变量，不能初始化普通变量
	// 1.初始化
	static {
		// 池基本配置
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(30);
		config.setMaxWaitMillis(30000);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

		String serverIP = "127.0.0.1";
		int port = 6379;
		//String password = "haiyisoft";

		JedisShardInfo jShardInfo = new JedisShardInfo(serverIP, port);
		//jShardInfo.setPassword(password);
		shards.add(jShardInfo);
		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	public List<String> blpopHdasAlertList() {
//		System.out.println("yes");
		try {
			ShardedJedis shardedJedis= shardedJedisPool.getResource(); 
			List<String> ll = shardedJedis.blpop(0,"myList");  //redis的队列名称，这里通过命令取
			shardedJedis.close();
			
			if(ll!=null && ll.size()>0){
				System.out.println("ll is not null");
				return ll;
			}else{
				System.out.println("ll is null");
				return null;
			}
		} catch (Exception e) {
			System.out.println("no");
			e.printStackTrace();
			return null;
		}
	}
	public int push(String listName,String content)
	{
		try {
			ShardedJedis shardedJedis= shardedJedisPool.getResource(); 
			shardedJedis.lpush(listName, content);
			return 1;
		} catch (Exception e)
		{
			return -1;
		}
		
	}

}

