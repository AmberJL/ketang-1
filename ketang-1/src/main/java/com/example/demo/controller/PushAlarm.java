package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.MessageService;
import com.example.demo.Service.UserService;
import com.example.demo.c.Match;
import com.example.demo.c.RedisClient;
import com.example.demo.data.messageData;

@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class PushAlarm implements ApplicationRunner {             //服务启动后自动加载该类

	@Autowired
    static UserService userService;
	private static MessageService message;
    @Autowired
    public void setMyServiceImpl(MessageService myService){
    	PushAlarm.message = myService;
    }
	
	 @Override
	    public void run(ApplicationArguments applicationArguments) throws Exception {
	        System.out.println("-------------->" + "项目启动，now=" + new Date());
	        myTimer();//心跳线程
	        messageQueue();
	    }
	 	public static void messageQueue() {
	 		Runnable run = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					//之前注释的
					while(true) {
						WebSocketServer webSocketServer = new WebSocketServer();
						RedisClient redisClient = new RedisClient();
						List<String> dataList = redisClient.blpopHdasAlertList();
						System.out.println(" size:"+dataList.size());
						if(dataList!=null && dataList.size()>0){
							String message = dataList.get(1);
							System.out.println("消息队列："+message);
							JSONObject res = JSONObject.parseObject(message);
							System.out.println("1");
			        	   messageData ms = new messageData();
			        	   System.out.println("2");
			        	   ms.setContent((String)res.get("content"));
			        	   ms.setFrom_user_id((String)res.get("from_user_id"));
			        	   ms.setState(0);
			        	   ms.setTime(Long.parseLong((String)res.get("time")));
			        	   ms.setTo_user_id((String)res.get("to_user_id"));
			        	   System.out.println("3");
			        	   PushAlarm.this.message.saveMessage(ms);
			        	   try {
			        		   System.out.println("4");
			        		   Iterator<Entry<Session,String>> iterator = WebSocketServer.map.entrySet().iterator();
				        	   System.out.println("iterator有next:"+iterator.hasNext());
			        		   while(iterator.hasNext())
				        	   {
				        		   System.out.println("进入了循环");
				        		   Entry<Session,String> entry = iterator.next();
				        		   System.out.println("当前检查的是"+entry.getValue());
				        		   //私聊
				        		   if(Match.match_mobile(ms.getTo_user_id())) {
				        			   //如果是要发送的这个人
				        			   if(entry.getValue().equals(ms.getTo_user_id()))
				        			   {
				        				   webSocketServer.sendMessage(entry.getKey(),message);
				        				   System.out.println("找到了");
				        				   break;
				        			   }
				        			   System.out.println("没有找到");
				        		   }
				        	   }
			        	   }catch(Exception e)
			        	   {
			        		   System.out.println("发送消息出错");
			        		   e.printStackTrace();
			        	   }
			        	  
					}
					
//						Iterator<Entry<Session,String>> iterator = WebSocketServer.map.entrySet().iterator();
//						Iterator<String> set = WebSocketServer.map.values().iterator();
//						while (set.hasNext()) {
//							System.out.println("登录的账户："+set.next());
//						}
//						WebSocketServer webSocketServer = new WebSocketServer();
//						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						System.out.println("推送时间："+format.format(new Date()));
//						if(type == 2)
//						{
//							while (iterator.hasNext()) {
//								Entry<Session,String> entry = iterator.next();
//								Session session = entry.getKey();
//								String userPower = entry.getValue();
//								System.out.println("推送内容："+message+" 退送用户："+userPower);
//								webSocketServer.sendMessage(session,message);
//							}
//						}
						
					}
				}
	 			
	 		};
	 		Thread n = new Thread(run);
	 		n.start();
	 	}
	    public static void myTimer(){
	    	
	    	
	    	Runnable run = new Runnable() {
				
				@Override
				public void run() {
					while(true){
						try {
							Iterator<Session> set = WebSocketServer.heart.keySet().iterator();
							while(set.hasNext()) {
								Session se = set.next();
								if(se != null) {
									long b = (long)WebSocketServer.heart.get(se);
									if( b < (new Date().getTime()-12000)) {
										System.out.println("即将关闭SessionId:"+se.getId());
										String name = WebSocketServer.map.get(se);
										System.out.println(name + "下线");
										se.close();
										WebSocketServer.map.remove(se);
										WebSocketServer.heart.remove(se);
									}
								}
								
							}
						}catch(Exception e) {
							System.out.println("心跳监测IOE");
							e.printStackTrace();
						}
					}					
				}
			};
			Thread newThread = new Thread(run);
			newThread.start();
	    }
}

