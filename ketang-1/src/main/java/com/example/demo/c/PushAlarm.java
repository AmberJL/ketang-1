package com.example.demo.c;

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
import com.example.demo.Service.UserService;

@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class PushAlarm implements ApplicationRunner {             //服务启动后自动加载该类

	@Autowired
    UserService userService;
	 @Override
	    public void run(ApplicationArguments applicationArguments) throws Exception {
	        System.out.println("-------------->" + "项目启动，now=" + new Date());
	        myTimer();
	    }

	    public static void myTimer(){
	    	
	    	//定时任务
	        /*Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                System.out.println("------定时任务--------");
	            }
	        }, 0, 1000*60);*/
	    	
	    	//阻塞队列
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
						
						//之前注释的
//						RedisClient redisClient = new RedisClient();
//						List<String> dataList = redisClient.blpopHdasAlertList();
//						System.out.println(" size:"+dataList.size());
//						if(dataList!=null && dataList.size()>0){
//							String message = dataList.get(1);
//							JSONObject ms = JSONObject.parseObject(message);
//							int type = ms.getIntValue("msgType");
//							System.out.println("type:"+type);						
//							Iterator<Entry<Session,String>> iterator = WebSocketServer.map.entrySet().iterator();
//							Iterator<String> set = WebSocketServer.map.values().iterator();
//							while (set.hasNext()) {
//								System.out.println("登录的账户："+set.next());
//							}
//							WebSocketServer webSocketServer = new WebSocketServer();
//							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//							System.out.println("推送时间："+format.format(new Date()));
//							if(type == 2)
//							{
//								while (iterator.hasNext()) {
//									Entry<Session,String> entry = iterator.next();
//									Session session = entry.getKey();
//									String userPower = entry.getValue();
//									System.out.println("推送内容："+message+" 退送用户："+userPower);
//									webSocketServer.sendMessage(session,message);
//								}
//							}else {
//								if(type == 1)
//								{
//									while (iterator.hasNext()) 
//									{
//										Entry<Session,String> entry = iterator.next();
//										Session session = entry.getKey();
//										String userPower = entry.getValue();
//										System.out.println("当前检查："+userPower+"to："+ms.getString("toUserId")+"是否一致"+userPower.equals(ms.getString("toUserId")));
//										if(userPower.equals(ms.getString("toUserId"))||userPower.equals(ms.getString("fromUserId")))
//										{
//											System.out.println("推送内容："+message+" 退送用户："+userPower);
//											webSocketServer.sendMessage(session,message);
//										}
//									}
//								}
//							}
//							
//						}
					}					
				}
			};
			Thread newThread = new Thread(run);
			newThread.start();
	    }
}

